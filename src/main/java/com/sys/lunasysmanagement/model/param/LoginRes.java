package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This is description.
 *
 * @author wangfangrui
 * @date 2019/8/29 18:11
 */
@Data
public class LoginRes {
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "cookieToken")
    private String cookieToken;
    @ApiModelProperty(value = "cookieUserId")
    private String cookieUserId;
}
