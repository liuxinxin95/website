//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cpy.website.center.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel
public class PageRequest implements Serializable {
    @NotNull(
        message = "页码不能为空"
    )
    @ApiModelProperty(
        value = "页码",
        example = "1"
    )
    private Integer pageNum;
    @NotNull(
        message = "分页大小不能为空"
    )
    @ApiModelProperty(
        value = "分页大小",
        example = "10"
    )
    private Integer pageSize;
    @ApiModelProperty(
        value = "是否开启行数统计,默认:true",
        allowableValues = "true,false",
        example = "true"
    )
    private Boolean countEnabled = true;

    public PageRequest() {
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getCountEnabled() {
        return this.countEnabled;
    }

    public void setCountEnabled(Boolean countEnabled) {
        this.countEnabled = countEnabled == null ? true : countEnabled;
    }
}
