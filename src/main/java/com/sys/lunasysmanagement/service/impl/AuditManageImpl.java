package com.sys.lunasysmanagement.service.impl;

import com.sys.lunasysmanagement.config.ExcelExport;
import com.sys.lunasysmanagement.constant.CommonMethod;
import com.sys.lunasysmanagement.constant.ConstantCode;
import com.sys.lunasysmanagement.constant.ResponseCode;
import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.mapper.AuditMapper;
import com.sys.lunasysmanagement.model.OperateLogModel;
import com.sys.lunasysmanagement.model.param.*;
import com.sys.lunasysmanagement.service.AuditManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 审核管理相关方法实现
 *
 * @author wangfangrui
 * @date 2019/8/27 10:50
 */
@Service
@Slf4j
public class AuditManageImpl implements AuditManageService {
    @Autowired
    AuditMapper auditMapper;

    @Autowired
    ExcelExport excelExport;

    /**
     * 审核信息查询
     */
    @Override
    public Result auditSearch(AuditQueryParam auditQueryParam) {
        try {
            if (auditQueryParam.getCurrentPage() == null || auditQueryParam.getPageSize() == null) {
                auditQueryParam.setCurrentPage(ConstantCode.DEFAULT_PAGE);
                auditQueryParam.setPageSize(ConstantCode.DEFAULT_PAGE_SIZE);
            }
            int currentPage = auditQueryParam.getCurrentPage();
            int pageSize = auditQueryParam.getPageSize();
            /**
             * 计算页面数据的起始条数
             * */
            auditQueryParam.setStartItemNo(pageSize * (currentPage - 1));
            List<AuditDisplayParam> auditDisplayParams = auditMapper.auditSearch(auditQueryParam);
            return Result.success(auditDisplayParams);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.SEARCH_FAILED);
        }
    }

    /**
     * 审核信息导出
     */
    @Override
    public Result auditExport(AuditQueryParam auditQueryParam, HttpServletResponse response) {
        try {
            List<AuditDisplayParam> auditDisplayParams = auditMapper.auditSearch(auditQueryParam);
            excelExport.export(ConstantCode.AUDIT_COLUMN_NAME, ConstantCode.AUDIT_SHEET_NAME, auditDisplayParams, response);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.EXPORT_FAILED);
        }
    }

    @Override
    public Result getDetail(String userId) {
        try {
            AuditDetailParam auditDetailParam = auditMapper.getDetail(userId);
            List<LogDisplayParam> operateLogParams = auditMapper.getLogs(userId);
            if (operateLogParams.size() != 0) {
                auditDetailParam.setLogList(operateLogParams);
            }
            return Result.success(auditDetailParam);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.SEARCH_FAILED);
        }
    }

    /**
     * 审核状态修改并进行日志记录
     */
    @Override
    public Result audit(AuditParam auditParam, HttpServletRequest request) {
        try {

            AuditDisplayParam auditDisplayParam = auditMapper.getTypeState(auditParam.getUserId());
            if (auditDisplayParam == null) {
                return Result.fail(ResponseCode.AUDIT_FAILED);
            }
            int type = auditDisplayParam.getType();
            int state = auditDisplayParam.getState();
            if (state != ConstantCode.APPROVE_PEND) {
                return Result.fail(ResponseCode.AUDIT_NO_CHANGE);
            }
            auditParam.setAuditTime(CommonMethod.getCurrentTime());
            if (type == ConstantCode.ORG_CODE) {
                auditMapper.updateOrgAudit(auditParam);
            } else if (type == ConstantCode.PERSON_CODE) {
                auditMapper.updatePersonAudit(auditParam);
            } else {
                return Result.fail(ResponseCode.AUDIT_FAILED);
            }
            OperateLogModel operateLogModel = new OperateLogModel();
            operateLogModel.setContent(ConstantCode.auditMap.get(auditParam.getState()));
            operateLogModel.setCreateTime(CommonMethod.getCurrentTime());
            operateLogModel.setOperatorId(CommonMethod.getUserId(request));
            operateLogModel.setUserId(auditParam.getUserId());
            auditMapper.updateOperateLog(operateLogModel);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(ResponseCode.AUDIT_FAILED);
        }

    }
}
