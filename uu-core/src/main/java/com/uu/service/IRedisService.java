package com.uu.service;

import com.alibaba.fastjson.JSONObject;
import com.uu.constant.RedisKeys;
import com.uu.exception.SCException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述: <br>
 *
 * @since: 1.0.0
 * @Author:Created By Clarence
 * @Date: 2019/7/1 17:23
 */
@Service
@Slf4j
public class IRedisService {
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;
    @Autowired
    protected RedisTemplate redisTemplate;


    /**
     * 写入redis缓存（不设置expire存活时间）
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("写入redis缓存失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * 写入redis缓存（设置expire存活时间）
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(final String key, String value, Long expire) {
        boolean result = false;
        try {
            ValueOperations operations = stringRedisTemplate.opsForValue();
            operations.set(key, value);
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("写入redis缓存（设置expire存活时间）失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * 读取redis缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        try {
            ValueOperations operations = stringRedisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            log.error("读取redis缓存失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * 判断redis缓存中是否有对应的key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        boolean result = false;
        try {
            result = stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断redis缓存中是否有对应的key失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * redis根据key删除对应的value
     *
     * @param key
     * @return
     */
    public boolean remove(final String key) {
        boolean result = false;
        try {
            if (exists(key)) {
                stringRedisTemplate.delete(key);
            }
            result = true;
        } catch (Exception e) {
            log.error("redis根据key删除对应的value失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * redis根据keys批量删除对应的value
     *
     * @param keys
     * @return
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 获取hash中field对应的值
     * 查询元素
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        Object val = redisTemplate.opsForHash().get(key, field);
        return val == null ? null : val.toString();
    }

    /**
     * 添加or更新hash的值
     * 添加元素
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 添加or更新hash的值
     * 添加元素
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, String value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除hash中field这一对kv
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    public boolean hHashKey(String key,String field){
        return redisTemplate.opsForHash().hasKey(key,field);
    }

    public Set<String> hGetKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 批量查询
     * <p>
     * 批量查询有两种，一个是全部捞出来，一个是捞出指定key的相关数据
     *
     * @param key
     * @return
     */
    public Map<String, String> hGetAll(String key) {
        return (Map<String, String>) redisTemplate.execute((RedisCallback<Map<String, String>>) con -> {
            Map<byte[], byte[]> result = con.hGetAll(key.getBytes());
            if (CollectionUtils.isEmpty(result)) {
                return new HashMap<>(0);
            }

            Map<String, String> ans = new HashMap<>(result.size());
            for (Map.Entry<byte[], byte[]> entry : result.entrySet()) {
                ans.put(new String(entry.getKey()), new String(entry.getValue()));
            }
            return ans;
        });
    }

    public Map<String, String> hmget(String key, List<String> fields) {
        List<String> result = redisTemplate.<String, String>opsForHash().multiGet(key, fields);
        Map<String, String> ans = new HashMap<>(fields.size());
        int index = -1;
        for (String field : fields) {
            ++index;
            if (result.get(index) == null) {
                continue;
            }
            ans.put(field, result.get(index));
        }
        return ans;
    }

    /**
     * 自增
     * hash的value如果是数字，提供了一个自增的方式，和String中的incr/decr差不多的效果
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public long hincr(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * value为列表的场景
     * hash + list
     * <p>
     * hash的value如果另外一种场景就是数组，目前没有找到特别友好的操作方式，只能在业务层进行兼容
     *
     * @param key
     * @param field
     * @return
     */
    public <T> List<T> hGetList(String key, String field, Class<T> obj) {
        Object value = redisTemplate.opsForHash().get(key, field);
        if (value != null) {
            return JSONObject.parseArray(value.toString(), obj);
        } else {
            return new ArrayList<>();
        }
    }

    public <T> void hSetList(String key, String field, List<T> values) {
        String v = JSONObject.toJSONString(values);
        redisTemplate.opsForHash().put(key, field, v);
    }

    public Set<String> keys(String prefix) {
        // 获取所有的key
        Set<String> keys = redisTemplate.keys(prefix);
        if (keys == null) {
            return new HashSet<>();
        }
        return keys;
    }


    public Long incr(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }



    public void checkVerCodeIsMatch(String phone , String verCode){
        //获取手机验证码
        String mobileCode = hget(RedisKeys.MOBILE_CODE,phone);
        if (StringUtils.isEmpty(mobileCode)) {
            throw new SCException("验证码已失效，请重新发送");
        }

        //获取校验失败次数
        int failTime = StringUtils.isEmpty(hget(RedisKeys.VER_FAIL_TIME,phone)) ? 0 : Integer.parseInt(hget(RedisKeys.VER_FAIL_TIME,phone));
        if( failTime == 0){
            hset(RedisKeys.VER_FAIL_TIME,phone,String.valueOf(failTime),1800); //设置验证次数 30分钟有效
        }

        if (failTime > 5) {
            throw new SCException("验证失败次数过多，请1小时以后再试");
        }

        if(!verCode.equals(mobileCode)){ //验证失败
            hincr(RedisKeys.VER_FAIL_TIME,phone,failTime);
            throw new SCException("验证失败，请核对验证码是否填写正确");
        }

        log.info("手机号{}，验证码{}，校验{}成功!",phone,verCode,mobileCode);

    }
}
