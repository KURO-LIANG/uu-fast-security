package com.uu.aspect;

import com.alibaba.fastjson.JSONArray;
import com.uu.constant.RedisKeys;
import com.uu.exception.SCException;
import com.uu.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: 请求频次切面处理类
* @Author: liang_qing
* @Email: clarence_liang@163.com
* @Date: 2021/4/15 下午5:31
* @Return
**/
@Aspect
@Slf4j
@Configuration
public class RequestAspect {
    @Resource
    private IRedisService iRedisService;

    @Around("execution(* com.uu.controller.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        boolean needCheck = false;
        if (!("get".equals(request.getMethod().toLowerCase()) || "options".equals(request.getMethod().toLowerCase()))) {
            needCheck = true;
            log.info("------- 请求频次环绕 START ------");
        }

//        String url = request.getRequestURL().toString();
        String methodName = point.getSignature().getName();
        //获取用户凭证
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        if(needCheck){
            //获取所有参数，添加请求限制
            needCheck = checkAndSaveAllParams(point,methodName,token);
        }

        Object result = point.proceed();

        if(needCheck){
            //删除请求限制
            requestEnd(methodName,token);
            log.info("------- 请求频次环绕 END ------");
        }

        return result;
    }

    private void requestEnd(String url, String token) {
        iRedisService.hdel(
                RedisKeys.getRequestKey(token),
                url
        );
    }

    private boolean checkAndSaveAllParams(ProceedingJoinPoint joinPoint, String methodName, String token) {
        //请求的参数
        Object[] args = joinPoint.getArgs();

        if(args != null && args.length > 0){
            List<Object> realArgs = new ArrayList<>();
            for (Object arg : args) {
                if (arg != null && !arg.getClass().getName().contains("HttpServletRequest")
                        && !arg.getClass().getName().contains("HttpServletResponse")
                        && !arg.getClass().getName().contains("ResponseFacade")) {
                    log.info("类名：{}",arg.getClass().getName());
                    realArgs.add(arg);
                }
            }

            if(realArgs.size() == 0){
                return false;
            }

            String params = JSONArray.toJSONString(realArgs);

            log.info("[请求TOKEN]：【{}】",token);
            log.info("[请求methodName]：【{}】",methodName);
            log.info("[参数]：【{}】",params);

            if(StringUtils.isNotBlank(params)){
                //sha256加密
                String encodeStr = DigestUtils.sha256Hex(params);
                log.info("[参数密文]：【{}】",encodeStr);

                //校验是否已存在
                String requestVal = iRedisService.hget(RedisKeys.getRequestKey(token),methodName);

                if(StringUtils.isNotBlank(requestVal) && requestVal.equals(encodeStr)){
                    throw new SCException("请勿操作过快！",-1);
                }

                // 存入缓存 10s后失效
                iRedisService.hset(
                        RedisKeys.getRequestKey(token),
                        methodName,
                        encodeStr,
                        10
                );

                return true;
            }
        }

        return false;
    }
}
