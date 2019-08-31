package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户灵活查询展示字段
 *
 * @author wangfangrui
 * @date 2019/8/23 14:27
 */
@Data
public class UserDisplayParam {
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 0:企业用户;1:个人用户
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "联系人")
    private String contact;
    @ApiModelProperty(value = "手机")
    private String mobile;
    @ApiModelProperty(value = "0 启用 1 禁用 2 删除")
    private Integer state;
    @ApiModelProperty(value = "tps")
    private String tps;
    @ApiModelProperty(value = "容量")
    private String storage;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "total")
    private Integer total;


}
