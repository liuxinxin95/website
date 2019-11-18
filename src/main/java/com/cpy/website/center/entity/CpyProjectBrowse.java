package com.cpy.website.center.entity;

import java.util.Date;
import javax.persistence.*;


/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Entity
@Table(name = "cpy_project_browse")
public class CpyProjectBrowse {


    /**
     *
     */
    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 浏览数
     */
    @Column(name = "browse_num")
    private Integer browseNum;

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


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
