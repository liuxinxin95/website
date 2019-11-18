//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cpy.website.framework;

public class ParamValidateException extends WebApiException {
    private String param;
    private String msg;

    public ParamValidateException(String param, String msg) {
        this.param = param;
        this.msg = msg;
    }

    public String getParam() {
        return this.param;
    }

    public String getMsg() {
        return this.msg;
    }
}
