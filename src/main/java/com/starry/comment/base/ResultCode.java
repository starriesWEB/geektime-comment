package com.starry.comment.base;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/11/25 15:03
 * @Description 枚举返回状态码、消息、数据
 */
public enum ResultCode implements Serializable {

    /**
     * 成功处理
     */
    OK(200, "SUCCESS"),

    /**
     * 处理失败
     */
    ERROR(500,"ERROR"),

    PasswordError(500,"用户名或密码错误"),
    InvalidArgs(500,"参数无效"),
    ErrorExcelField(500,"EXCEL字段错误")
    ;

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
