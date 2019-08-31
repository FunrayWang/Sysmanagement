package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 审核详情界面日志展示字段
 *
 * @author wangfangrui
 * @date 2019/8/28 14:06
 */
@Data
public class LogDisplayParam {

    @ApiModelProperty(value = "管理员名称")
    private String adminName;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "内容")
    private String content;
}
