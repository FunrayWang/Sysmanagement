package com.sys.lunasysmanagement.controller;

import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.controller.base.BaseController;
import com.sys.lunasysmanagement.model.param.EditParam;
import com.sys.lunasysmanagement.model.param.UserQueryParam;
import com.sys.lunasysmanagement.service.UserManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客户信息管理相关接口
 *
 * @author wangfangrui
 * @date 2019/8/22 17:11
 */
@RestController
@RequestMapping("/userManage")
@Slf4j
public class UserManageController extends BaseController {

    @Autowired
    UserManageService userManageService;

    /**
     * 客户信息查询
     * 查询成功:0
     * 查询失败:1007
     */
    @PostMapping("search")
    @ResponseBody
    @ApiOperation(value = "客户信息查询", notes = "客户信息查询")
    @ApiImplicitParam(name = "UserQueryParam", value = "客户信息查询", required = true, dataType = "UserQueryParam")
    public Result userSearch(@RequestBody UserQueryParam userQueryParam) {
        return userManageService.userSearch(userQueryParam);
    }

    /**
     * 客户信息数据导出excel
     * 导出失败:1008
     */
    @PostMapping("export")
    @ResponseBody
    @ApiOperation(value = "客户信息数据导出excel", notes = "客户信息数据导出excel")
    @ApiImplicitParam(name = "UserQueryParam", value = "客户信息数据导出excel", required = true, dataType = "UserQueryParam")
    public Result userExport(@RequestBody UserQueryParam userQueryParam) {
        return userManageService.userExport(userQueryParam, response);
    }

    /**
     * 客户信息编辑,可对状态、TPS、存储空间进行编辑
     * 编辑成功:0
     * 编辑失败:1009
     */
    @PostMapping("edit")
    @ResponseBody
    @ApiOperation(value = "客户信息编辑", notes = "客户信息编辑")
    @ApiImplicitParam(name = "EditParam", value = "客户信息编辑", required = true, dataType = "EditParam")
    public Result editUser(@RequestBody @Valid EditParam editParam, BindingResult bindingResult) {
        return userManageService.editUser(editParam, request);
    }

    /**
     * 客户逻辑删除
     * 删除成功:0
     * 删除失败:1010
     */
    @GetMapping("delete")
    @ResponseBody
    @ApiOperation(value = "客户逻辑删除", notes = "客户逻辑删除")
    @ApiImplicitParam(name = "String", value = "客户逻辑删除", required = true, dataType = "String")
    public Result deleteUser(String userId) {
        return userManageService.deleteUser(userId);
    }
}
