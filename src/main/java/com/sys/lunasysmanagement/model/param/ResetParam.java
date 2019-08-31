package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 密码重置输入变量.
 *
 * @author wangfangrui
 * @date 2019/8/22 15:23
 */
@Data
@Valid
public class ResetParam {
    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "密码不能为空")
    private String userPwd;
}
