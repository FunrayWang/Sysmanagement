package com.sys.lunasysmanagement.constant;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回对象.
 *
 * @author wangfangrui
 * @date 2019/8/22 13:58
 */
@Data
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResponseCode responseCode, T data) {
        this(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public Result(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMsg(), null);
    }

    public static Result success() {
        return new Result(ResponseCode.SUCCESS);
    }

    public static Result success(Object data) {
        return new Result(ResponseCode.SUCCESS, data);
    }

    public static Result fail(ResponseCode responseCode, Object data) {
        return new Result(responseCode, data);
    }

    public static Result fail(ResponseCode responseCode) {
        return new Result(responseCode);
    }

    public static Result fail(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }


}
