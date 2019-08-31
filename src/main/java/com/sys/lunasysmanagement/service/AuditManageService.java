package com.sys.lunasysmanagement.service;

import com.sys.lunasysmanagement.constant.Result;
import com.sys.lunasysmanagement.model.param.AuditParam;
import com.sys.lunasysmanagement.model.param.AuditQueryParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is description.
 *
 * @author wangfangrui
 * @date 2019/8/27 10:49
 */
public interface AuditManageService {
    Result auditSearch(AuditQueryParam auditQueryParam);

    Result auditExport(AuditQueryParam auditQueryParam, HttpServletResponse response);

    Result getDetail(String userId);

    Result audit(AuditParam auditParam, HttpServletRequest request);
}
