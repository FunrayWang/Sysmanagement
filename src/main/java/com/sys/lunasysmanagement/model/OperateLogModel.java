package com.sys.lunasysmanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * operate_log表字段(审核日志表字段).
 *
 * @author wangfangrui
 * @date 2019/8/28 16:36
 */
@Data
public class OperateLogModel {

    @ApiModelProperty(value = "主键编号")
    private Integer id;
    @ApiModelProperty(value = "日志编号")
    private String logId;
    @ApiModelProperty(value = "用户编号")
    private String userId;
    @ApiModelProperty(value = "审核人编号")
    private String operatorId;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
