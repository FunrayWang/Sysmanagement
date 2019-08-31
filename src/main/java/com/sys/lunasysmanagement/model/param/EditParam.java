package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 客户信息管理界面编辑字段
 *
 * @author wangfangrui
 * @date 2019/8/24 15:37
 */
@Data
@Valid
public class EditParam {
    @ApiModelProperty(value = "用户编号")
    @NotNull(message = "用户id不能为空")
    private String userId;
    @ApiModelProperty(value = "tps")
    private String tps;
    @ApiModelProperty(value = "容量")
    private String storage;
    @ApiModelProperty(value = "状态")
    private Integer state;

}
