package com.sys.lunasysmanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * sys_admin_user表字段(管理员表).
 *
 * @author wangfangrui
 * @date 2019/8/21 15:55
 */
@Data
public class SysAdminUserModel {
    @ApiModelProperty(value = "主键编号")
    private Integer id;
    @ApiModelProperty(value = "用户编号")
    private String userId;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String userPwd;
    @ApiModelProperty(value = "用户类型")
    private Integer userType;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "是否首次登录")
    private Integer init;
}
