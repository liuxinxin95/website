package com.cpy.website.center.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;


/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Entity
@Table(name = "cpy_article")
@Data
public class CpyArticle {


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 关键字
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 摘要
     */
    @Column(name = "digest")
    private String digest;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 图片
     */
    @Column(name = "pics")
    private String pics;

    /**
     * 分类id集合
     */
    @Column(name = "category_ids")
    private String categoryIds;

    /**
     * 标签集合
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 状态：0待审核1已发布
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     *
     */
    @Column(name = "update_time")
    private Date updateTime;


}
