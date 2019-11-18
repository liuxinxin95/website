package com.cpy.website.framework;

public class  StatusCodeException extends WebBaseException {
    private int code;
    private String msg;

    public StatusCodeException(int code) {
        this.code = code;
    }

    public StatusCodeException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StatusCodeException(ApiStatusCode statusCode, String msg) {
        this.code = statusCode.value();
        this.msg = msg;
    }

    public StatusCodeException(int code, String msg, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
