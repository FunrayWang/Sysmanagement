package com.sys.lunasysmanagement.controller;

import com.sys.lunasysmanagement.constant.ResponseCode;
import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.controller.base.BaseController;
import com.sys.lunasysmanagement.model.param.LoginParam;
import com.sys.lunasysmanagement.model.param.ResetParam;
import com.sys.lunasysmanagement.service.LoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 登录相关操作.
 *
 * @author wangfangrui
 * @date 2019/8/21 15:19
 */
@RestController
@RequestMapping("/userLogin")
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    LoginService loginService;

    /**
     * 管理员登录接口
     * 首次登录:1004
     * 登录成功:0
     * 登录失败,密码错误:1011
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "管理员登录接口", notes = "管理员登录接口")
    @ApiImplicitParam(name = "LoginParam", value = "管理员登录接口", required = true, dataType = "LoginParam")
    public Result adminLogin(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult) {
        return loginService.adminLogin(loginParam, response);
    }

    /**
     * 密码重置接口
     * 重置成功:0
     * 重置失败:1005
     */
    @PostMapping("reset")
    @ResponseBody
    @ApiOperation(value = "密码重置接口", notes = "密码重置接口")
    @ApiImplicitParam(name = "ResetParam", value = "密码重置接口", required = true, dataType = "ResetParam")
    public Result resetPwd(@Valid @RequestBody ResetParam resetParam, BindingResult bindingResult) {
        return loginService.resetPwd(resetParam, request);
    }

    /**
     * 登出接口
     * 登出成功:0
     * 登出失败:1006
     */
    @GetMapping("logout")
    @ResponseBody
    @ApiOperation(value = "登出接口", notes = "登出接口")
    @ApiImplicitParam(name = "ResetParam", value = "登出接口", required = true, dataType = "ResetParam")
    public Result adminLogOut() {
        if (loginService.adminLogOut(request)) {
            return Result.success();
        }
        return Result.fail(ResponseCode.LOG_OUT_FAILED);
    }

}
