package com.cpy.website.center.service;

import com.cpy.website.center.entity.CpyProjectBrowse;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyProjectBrowseService {
    /**
    * 根据ID查询
    * @return
    */
     CpyProjectBrowse queryObject(Integer projectId);

    /**
    * 查询列表
    * @param map
    * @return
    */
    List<CpyProjectBrowse> queryList(Map<String, Object> map);

    /**
    * 保存
    * @throws Exception
    */
    void save(CpyProjectBrowse CpyProjectBrowse) throws Exception;

    /**
    * 更新
    * @throws Exception
    */
    void update(CpyProjectBrowse CpyProjectBrowse) throws Exception;

    /**
    * 删除
    * @throws Exception
    */
    void delete(Integer projectId) throws Exception;

    /**
    * 批量删除
    * @throws Exception
    */
    void deleteBatch(Integer[] projectIds) throws Exception;
}
