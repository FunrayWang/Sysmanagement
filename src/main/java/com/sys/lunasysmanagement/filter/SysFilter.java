package com.sys.lunasysmanagement.filter;

import com.alibaba.fastjson.JSONObject;
import com.sys.lunasysmanagement.config.RedisService;
import com.sys.lunasysmanagement.constant.ConstantCode;
import com.sys.lunasysmanagement.constant.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录控制.
 *
 * @author wangfangrui
 * @date 2019/8/21 15:15
 */
@Slf4j
public class SysFilter implements Filter {
    private static final String[] NOT_FILTER = {
            "js", "gif", "jpg", "png", "css", "ico", "html", "/login","swagger","/v2/api-docs"
    };
    private static RedisService redisService;

    @Override
    public void init(FilterConfig filterConfig) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        redisService = context.getBean(RedisService.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "CONTENT-TYPE");
        String method = request.getServletPath();
        for (String str : NOT_FILTER) {
            if (method.indexOf(str) != -1) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String token = "";
            String userId = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantCode.COOKIE_TOKEN)) {
                    token = cookie.getValue();
                    log.info("token=" + token);
                }
                if (cookie.getName().equals(ConstantCode.COOKIE_USERID)) {
                    userId = cookie.getValue();
                    log.info("cookie=" + userId);
                }
            }
            if (!token.equals("") && !userId.equals("") && redisService.get(token) != null) {
                String redisUserId = redisService.get(token).toString();
                if (redisUserId.equals(userId)) {
                    filterChain.doFilter(request, response);
                    return;
                }

            }
        }
        writer(response, ResponseCode.NOT_LOGIN);

    }

    @Override
    public void destroy() {

    }

    private static void writer(HttpServletResponse response, ResponseCode responseCode) {
        JSONObject json = new JSONObject();
        json.put("code", responseCode.getCode());
        json.put("msg", responseCode.getMsg());
        writeJson(response, json);
    }

    private static void writeJson(HttpServletResponse response, JSONObject jsonObject) {
        try {
            response.setContentType("application/octet-stream");
            response.setContentType("application/json;charset=utf-8");
            String message = jsonObject.toString();
            response.getWriter().write(message);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
