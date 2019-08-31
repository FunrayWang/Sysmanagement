package com.sys.lunasysmanagement.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Controller基类
 *
 * @author wangfangrui
 * @date 2019/8/29 9:19
 */
@Controller
public class BaseController {

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    /**
     * 请求之前的属性赋值
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
    }
}
