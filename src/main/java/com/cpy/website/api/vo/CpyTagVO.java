package com.cpy.website.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;


/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@ApiModel
@Data
public class CpyTagVO {

    private Integer id;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String name;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    private String alias;

    /**
     * 摘要
     */
    @ApiModelProperty(value = "别名")
    private String digest;


}
