package com.sys.lunasysmanagement.service.impl;

import com.sys.lunasysmanagement.config.RedisService;
import com.sys.lunasysmanagement.constant.CommonMethod;
import com.sys.lunasysmanagement.constant.ConstantCode;
import com.sys.lunasysmanagement.constant.ResponseCode;
import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.mapper.SysUserMapper;
import com.sys.lunasysmanagement.model.SysAdminUserModel;
import com.sys.lunasysmanagement.model.param.LoginParam;
import com.sys.lunasysmanagement.model.param.LoginRes;
import com.sys.lunasysmanagement.model.param.ResetParam;
import com.sys.lunasysmanagement.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录相关操作服务层.
 *
 * @author wangfangrui
 * @date 2019/8/21 15:43
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisService redisService;

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public Result adminLogin(LoginParam loginParam, HttpServletResponse response) {
        String userName = loginParam.getUserName();
        String pwd = DigestUtils.md5DigestAsHex(loginParam.getUserPwd().getBytes());
        SysAdminUserModel sysAdminUser = sysUserMapper.loginCheck(userName, pwd);
        /**
         * 判断用户密码是否正确
         * */
        if (sysAdminUser != null) {
            String userId = sysAdminUser.getUserId();
            String token = getToken();
            setCookie(token, userId, response);
            redisService.set(token, userId, ConstantCode.REDIS_EXPIRED_TIME);
            LoginRes loginRes = new LoginRes();
            loginRes.setCookieToken(token);
            loginRes.setCookieUserId(userId);
            loginRes.setUserName(userName);
            /**
             * 判断是否首次登录
             * */
            if (sysAdminUser.getInit() == ConstantCode.FIRST_LOGIN) {
                return new Result(ResponseCode.FIRST_LOGIN, loginRes);
            }

            return Result.success(loginRes);
        } else {
            return Result.fail(ResponseCode.PWD_FAILED);
        }
    }

    /**
     * 重置密码
     */
    @Override
    public Result resetPwd(ResetParam resetParam, HttpServletRequest request) {

        String userId = CommonMethod.getUserId(request);
        if (userId == null) {
            return Result.fail(ResponseCode.NOT_LOGIN);
        }
        String pwd = DigestUtils.md5DigestAsHex(resetParam.getUserPwd().getBytes());
        if (sysUserMapper.resetPwd(userId, pwd) == 1) {
            if (adminLogOut(request)) {
                return Result.success();
            }
        }
        return Result.fail(ResponseCode.RESET_FAILED);
    }

    /**
     * 登出操作,删除redis中token
     */
    @Override
    public boolean adminLogOut(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        boolean success = true;
        if (cookies != null) {
            String token = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ConstantCode.COOKIE_TOKEN)) {
                    token = cookie.getValue();
                }
            }
            //重置密码后删除原token
            if (!token.equals("")) {
                success = redisService.remove(token);
                log.info("token has been removed from redis");
            }
        }
        return success;
    }

    /**
     * 设置cookie
     */
    private void setCookie(String token, String userId, HttpServletResponse response) {
        Cookie tokenCookie = new Cookie(ConstantCode.COOKIE_TOKEN, token);
        Cookie userCookie = new Cookie(ConstantCode.COOKIE_USERID, userId);
        tokenCookie.setPath("/");
        userCookie.setPath("/");
        tokenCookie.setMaxAge(ConstantCode.COOKIE_EXPIRED_TIME);
        userCookie.setMaxAge(ConstantCode.COOKIE_EXPIRED_TIME);
        response.addCookie(tokenCookie);
        response.addCookie(userCookie);
    }

    /**
     * 生成唯一token
     */
    private String getToken() {
        StringBuffer token = new StringBuffer();
        token.append(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        return token.toString();
    }
}
