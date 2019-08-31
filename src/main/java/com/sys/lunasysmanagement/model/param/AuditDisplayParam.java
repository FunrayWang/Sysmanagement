package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户信息审核界面展示字段
 *
 * @author wangfangrui
 * @date 2019/8/27 10:52
 */
@Data
public class AuditDisplayParam {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "0 企业用户 1 个人用户")
    private Integer type;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "1 待审核 2 审核拒绝 3 审核通过")
    private Integer state;
    @ApiModelProperty(value = "审核人姓名")
    private String auditName;
    @ApiModelProperty(value = "审核时间")
    private String auditTime;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
