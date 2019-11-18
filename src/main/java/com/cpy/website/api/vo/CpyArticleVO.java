package com.cpy.website.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Data
@ApiModel
public class CpyArticleVO {


    /**
     *
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    private String keyword;

    /**
     * 摘要
     */
    @ApiModelProperty(value = "摘要")
    private String digest;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private List<String> picList;

    /**
     * 分类id集合
     */
    @ApiModelProperty(value = "分类id集合")
    private List<String> categoryIdList;

    /**
     * 标签集合
     */
    @ApiModelProperty(value = "标签集合")
    private List<String> tagList;

    /**
     * 状态：0待审核1已发布
     */
    @ApiModelProperty(value = "状态：0待审核1已发布")
    private Integer status;

    @ApiModelProperty(value = "浏览量")
    private Integer browseNum;


}
