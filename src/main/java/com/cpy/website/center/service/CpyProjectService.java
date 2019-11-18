package com.cpy.website.center.service;

import com.cpy.website.api.param.QueryProjectParam;
import com.cpy.website.center.entity.CpyProject;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyProjectService {
    /**
    * 根据ID查询
    * @return
    */
     CpyProject queryObject(Integer id);

    /**
    * 查询列表
    * @param map
    * @return
    */
    List<CpyProject> queryList(QueryProjectParam param);

    /**
    * 保存
    * @throws Exception
    */
    void save(CpyProject CpyProject) throws Exception;

    /**
    * 更新
    * @throws Exception
    */
    void update(CpyProject CpyProject) throws Exception;

    /**
    * 删除
    * @throws Exception
    */
    void delete(Integer id) throws Exception;

    /**
    * 批量删除
    * @throws Exception
    */
    void deleteBatch(Integer[] ids) throws Exception;
}
