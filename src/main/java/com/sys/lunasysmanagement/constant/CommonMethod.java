package com.sys.lunasysmanagement.constant;

import org.apache.http.util.TextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公用方法
 *
 * @author wangfangrui
 * @date 2019/8/27 16:19
 */
public class CommonMethod {
    /**
     * 获取登录用户id
     */
    public static String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ConstantCode.COOKIE_USERID)) {
                String userId = cookie.getValue();
                return userId;
            }
        }
        return null;
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    public static boolean isEmpty(String... strs) {
        if (strs == null || strs.length == 0) {
            return true;
        }

        for (String str : strs) {
            if (str.trim().equals("")) {
                return true;
            }
            if (TextUtils.isEmpty(str)) {
                return true;
            }
        }
        return false;
    }
}
