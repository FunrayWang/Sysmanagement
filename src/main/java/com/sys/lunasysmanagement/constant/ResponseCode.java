package com.sys.lunasysmanagement.constant;

/**
 * 自定义返回状态值.
 *
 * @author wangfangrui
 * @date 2019/8/21 16:41
 */
public enum ResponseCode {
    SUCCESS(0, "成功"),
    LOGIN_FAILED(1001, "用户登录失败"),
    NOT_LOGIN(1002, "用户未登录"),
    FIRST_LOGIN(1004, "首次登录"),
    RESET_FAILED(1005, "密码重置失败"),
    LOG_OUT_FAILED(1006, "登出失败"),
    SEARCH_FAILED(1007, "查询失败"),
    EXPORT_FAILED(1008, "导入失败"),
    UPDATE_FAILED(1009, "更新失败"),
    DELETE_FAILED(1010, "删除失败"),
    PWD_FAILED(1011, "密码错误"),
    AUDIT_FAILED(1012, "审核失败"),
    ERROR_PARAM(1013, "参数错误"),
    AUDIT_NO_CHANGE(1014, "无法再次审核");

    private int code;
    private String msg;

    private ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
