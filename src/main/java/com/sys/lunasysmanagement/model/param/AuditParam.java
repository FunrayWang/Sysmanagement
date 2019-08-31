package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 审核字段
 *
 * @author wangfangrui
 * @date 2019/8/28 15:47
 */
@Data
@Valid
public class AuditParam {
    @NotNull(message = "userId不能为空")
    @ApiModelProperty(value = "用户编号")
    private String userId;
    @ApiModelProperty(value = "建议")
    private String advice;
    @ApiModelProperty(value = "审核时间")
    private String auditTime;
    @ApiModelProperty(value = "1 待审核 2 审核拒绝 3 审核通过")
    private Integer state;
}
