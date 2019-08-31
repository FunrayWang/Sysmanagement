package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 客户信息审核详情界面展示字段
 *
 * @author wangfangrui
 * @date 2019/8/28 9:56
 */
@Data
public class AuditDetailParam {
    @ApiModelProperty(value = "个人名称")
    private String personName;
    @ApiModelProperty(value = "个人代码")
    private String personCode;
    @ApiModelProperty(value = "个人联系人")
    private String personContact;
    @ApiModelProperty(value = "个人手机")
    private String personMobile;
    @ApiModelProperty(value = "个人状态")
    private Integer personState;
    @ApiModelProperty(value = "个人意见")
    private String personAdvice;

    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "法人")
    private String legalPerson;
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "企业名称")
    private String orgName;
    @ApiModelProperty(value = "企业信用代码")
    private String orgCode;
    @ApiModelProperty(value = "企业联系人")
    private String orgContact;
    @ApiModelProperty(value = "企业手机")
    private String orgMobile;
    @ApiModelProperty(value = "企业状态")
    private Integer orgState;
    @ApiModelProperty(value = "企业意见")
    private String orgAdvice;
    @ApiModelProperty(value = "日志输出列表")
    private List<LogDisplayParam> logList;

}
