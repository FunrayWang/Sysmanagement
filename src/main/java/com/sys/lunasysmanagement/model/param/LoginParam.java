package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 登录相关输入变量.
 *
 * @author wangfangrui
 * @date 2019/8/22 15:17
 */
@Data
@Valid
public class LoginParam {
    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "密码不能为空")
    private String userPwd;
}
