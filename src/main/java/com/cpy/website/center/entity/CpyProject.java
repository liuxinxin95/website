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
@Table(name = "cpy_project")
@Data
public class CpyProject {


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
     * 成功案例网站
     */
    @Column(name = "succeed_url")
    private String succeedUrl;

    /**
     * 是否公开
     */
    @Column(name = "is_open")
    private Boolean isOpen;

    /**
     * 密码
     */
    @Column(name = "pwds")
    private String pwds;

    /**
     * pc
     */
    @Column(name = "pc")
    private Boolean pc;

    /**
     * 手机
     */
    @Column(name = "iphone")
    private Boolean iphone;

    /**
     * 平板
     */
    @Column(name = "ipad")
    private Boolean ipad;

    /**
     * 成功案例网站
     */
    @Column(name = "pc_url")
    private String pcUrl;

    /**
     * 成功案例网站
     */
    @Column(name = "iphone_url")
    private String iphoneUrl;

    /**
     * 成功案例网站
     */
    @Column(name = "ipad_url")
    private String ipadUrl;

    /**
     * 状态：0待审核1已发布
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 分类id集合
     */
    @Column(name = "category_ids")
    private String categoryIds;

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
