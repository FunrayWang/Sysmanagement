package com.sys.lunasysmanagement.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户灵活查询条件搜索参数
 *
 * @author wangfangrui
 * @date 2019/8/22 17:19
 */
@Data
public class UserQueryParam {
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "0:企业用户;1:个人用户")
    private Integer type;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "状态")
    private Integer state;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "当前页面")
    private Integer currentPage;
    @ApiModelProperty(value = "页面开始条数")
    private Integer pageSize;
    @ApiModelProperty(value = "分页开始条数")
    private Integer startItemNo;

}
