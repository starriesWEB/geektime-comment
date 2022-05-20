package com.starry.comment.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/11/25 15:21
 * @Description 统一返回结果
 */
@Data
public class R implements Serializable {

    private Integer code;
    private String msg;
    private Object data;


    public static R ok() {
        R r = new R();
        r.setCode(ResultCode.OK.getCode());
        r.setMsg(ResultCode.OK.getMsg());
        r.setData(null);
        return r;
    }

    /**
     * 成功返回并携带数据
     * @param object
     * @return
     */
    public static R ok(Object object) {
        R r = new R();
        r.setCode(ResultCode.OK.getCode());
        r.setMsg(ResultCode.OK.getMsg());
        r.setData(object);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCode.ERROR.getCode());
        r.setMsg(ResultCode.ERROR.getMsg());
        r.setData(null);
        return r;
    }

    public static R errorWithLogin() {
        R r = new R();
        r.setCode(ResultCode.PasswordError .getCode());
        r.setMsg(ResultCode.PasswordError.getMsg());
        r.setData(null);
        return r;
    }

    public static R errorInvalidArgs() {
        R r = new R();
        r.setCode(ResultCode.InvalidArgs.getCode());
        r.setMsg(ResultCode.InvalidArgs.getMsg());
        r.setData(null);
        return r;
    }

    public static R errorExcelField() {
        R r = new R();
        r.setCode(ResultCode.ErrorExcelField.getCode());
        r.setMsg(ResultCode.ErrorExcelField.getMsg());
        r.setData(null);
        return r;
    }

    /**
     * 自定义错误状态码和错误提示
     * @param code
     * @param msg
     * @return
     */
    public static R error(Integer code ,String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    /**
     * 自定义错误提示
     * @param msg
     * @return
     */
    public static R error(String msg) {
        R r = new R();
        r.setCode(ResultCode.ERROR.getCode());
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}
