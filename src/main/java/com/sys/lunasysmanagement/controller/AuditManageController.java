package com.sys.lunasysmanagement.controller;

import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.controller.base.BaseController;
import com.sys.lunasysmanagement.model.param.AuditParam;
import com.sys.lunasysmanagement.model.param.AuditQueryParam;
import com.sys.lunasysmanagement.service.AuditManageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 客户信息审核相关接口
 *
 * @author wangfangrui
 * @date 2019/8/27 10:40
 */
@RestController
@RequestMapping("/auditManage")
@Slf4j
public class AuditManageController extends BaseController {
    @Autowired
    AuditManageService auditManageService;

    /**
     * 搜索
     * 查询成功:0
     * 查询失败:1007
     */
    @PostMapping("/search")
    @ResponseBody
    @ApiOperation(value = "搜索", notes = "搜索")
    @ApiImplicitParam(name = "AuditQueryParam", value = "搜索", required = true, dataType = "AuditQueryParam")
    public Result auditSearch(@RequestBody @Valid AuditQueryParam auditQueryParam, BindingResult bindingResult) {
        return auditManageService.auditSearch(auditQueryParam);
    }

    /**
     * excel导出
     * 导出失败:1008
     */
    @PostMapping("/export")
    @ResponseBody
    @ApiOperation(value = "excel导出", notes = "excel导出")
    @ApiImplicitParam(name = "AuditQueryParam", value = "excel导出", required = true, dataType = "AuditQueryParam")
    public Result auditExport(@RequestBody @Valid AuditQueryParam auditQueryParam, BindingResult bindingResult) {
        return auditManageService.auditExport(auditQueryParam, response);
    }

    /**
     * 获取用户详细信息
     * 获取成功:0
     * 获取失败:1007
     */
    @PostMapping("/detail")
    @ResponseBody
    @ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息")
    @ApiImplicitParam(name = "String", value = "获取用户详细信息", required = true, dataType = "String")
    public Result getDetail(@RequestParam String userId) {
        return auditManageService.getDetail(userId);
    }

    /**
     * 用户审核
     * 审核成功:0
     * 审核失败:1012
     * 无法再次审核:1014
     */
    @PostMapping("/audit")
    @ResponseBody
    public Result audit(@RequestBody @Valid AuditParam auditParam, BindingResult bindingResult) {
        return auditManageService.audit(auditParam, request);
    }
}
