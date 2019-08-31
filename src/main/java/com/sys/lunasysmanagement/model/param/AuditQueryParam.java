package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * 客户信息审核界面查询字段
 *
 * @author wangfangrui
 * @date 2019/8/27 10:42
 */
@Data
@Valid
public class AuditQueryParam {
    @ApiModelProperty(value = "邮件")
    private String email;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "状态")
    private Integer state;
    @ApiModelProperty(value = "审核人名称")
    private String auditName;
    @ApiModelProperty(value = "开始审核时间")
    private String startAuditTime;
    @ApiModelProperty(value = "结束审核时间")
    private String endAuditTime;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "当前页面")
    @Min(value = 1, message = "页数必须大于0")
    private Integer currentPage;
    @ApiModelProperty(value = "页面条数")
    @Min(value = 1, message = "条数必须大于0")
    private Integer pageSize;
    @ApiModelProperty(value = "分页开始条数")
    private Integer startItemNo;

}
