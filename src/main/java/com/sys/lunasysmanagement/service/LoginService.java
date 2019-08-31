package com.sys.lunasysmanagement.service;

import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.model.param.LoginParam;
import com.sys.lunasysmanagement.model.param.ResetParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录相关操作汇总.
 *
 * @author wangfangrui
 * @date 2019/8/21 15:42
 */
public interface LoginService {
    /**
     * 用户登录
     */
    Result adminLogin(LoginParam loginParam, HttpServletResponse response);

    /**
     * 密码重置
     */
    Result resetPwd(ResetParam resetParam, HttpServletRequest request);

    /**
     * 用户登出
     */
    boolean adminLogOut(HttpServletRequest request);
}
