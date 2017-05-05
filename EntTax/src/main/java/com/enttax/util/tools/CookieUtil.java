package com.enttax.util.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by brainy on 16-11-29.
 */
public class CookieUtil {

    /**
     * 默认最长保存的时间为一周
     */
    public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7;

    private static final CookieUtil mInstance = new CookieUtil();

    private CookieUtil() {
        super();
    }

    public static CookieUtil getInstance() {

        return mInstance;
    }

    /**
     * @param response //请求响应
     * @param name     //Cookie的名字
     * @param value    //Cookie的值
     * @param maxAge   //Cookie 的生命周期
     */
    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {

        Cookie cookie = new Cookie(name, value);

        cookie.setPath("/");

        if (maxAge > 0) {

            cookie.setMaxAge(maxAge);
        }

        response.addCookie(cookie);
    }

    /**
     * @param request //请求
     * @param name    //Cookie的名字
     * @return //对应的Cookie
     */
    public Cookie findCookieByCookieName(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals(name)) {

                return cookie;
            }
        }

        return null;
    }
}
