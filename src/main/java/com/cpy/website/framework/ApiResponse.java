//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cpy.website.framework;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ApiResponse<T> {
    @ApiModelProperty(
            value = "状态码",
            position = 0,
            required = true,
            example = "2000"
    )
    private Integer statusCode;
    @ApiModelProperty(
            value = "错误消息",
            position = 1
    )
    private String msg;
    @ApiModelProperty(
            value = "错误跟踪消息",
            position = 2
    )
    private String traceMsg;
    @ApiModelProperty(
            value = "响应创建时间戳",
            position = 3
    )
    private Long timestamp;
    @ApiModelProperty(
            value = "签名类型",
            position = 4
    )
    private String signType;
    @ApiModelProperty(
            value = "签名",
            position = 5
    )
    private String sign;
    @ApiModelProperty(
            value = "响应数据",
            position = 6
    )
    private T body;

    public ApiResponse() {
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSignType() {
        return this.signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getTraceMsg() {
        return this.traceMsg;
    }

    public void setTraceMsg(String traceMsg) {
        this.traceMsg = traceMsg;
    }
}
