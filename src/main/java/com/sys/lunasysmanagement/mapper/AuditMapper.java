package com.sys.lunasysmanagement.mapper;

import com.sys.lunasysmanagement.model.OperateLogModel;
import com.sys.lunasysmanagement.model.param.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 审核相关表操作
 *
 * @author wangfangrui
 * @date 2019/8/27 10:51
 */
@Repository
public interface AuditMapper {
    List<AuditDisplayParam> auditSearch(AuditQueryParam auditQueryParam);

    AuditDetailParam getDetail(@Param("userId") String userId);

    List<LogDisplayParam> getLogs(@Param("userId") String userId);

    int updateOrgAudit(AuditParam auditParam);

    int updatePersonAudit(AuditParam auditParam);

    AuditDisplayParam getTypeState(@Param("userId") String userId);

    int updateOperateLog(OperateLogModel operateLogModel);
}
