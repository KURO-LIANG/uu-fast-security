package com.uu.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求信息
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        return servletRequestAttributes != null ? servletRequestAttributes.getRequest() : null;
    }

    public static String getDomain() {
        HttpServletRequest request = getHttpServletRequest();

        if(request == null) return "";

        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        return request != null ? request.getHeader("Origin") : "";
    }
}
