package com.cpy.website.center.service;

import com.cpy.website.center.entity.CpyCategory;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyCategoryService {
    /**
    * 根据ID查询
    * @return
    */
     CpyCategory queryObject(Integer id);

    /**
    * 查询列表
    * @param map
    * @return
    */
    List<CpyCategory> queryList(Map<String, Object> map);

    /**
    * 保存
    * @throws Exception
    */
    void save(CpyCategory CpyCategory) throws Exception;

    /**
    * 更新
    * @throws Exception
    */
    void update(CpyCategory CpyCategory) throws Exception;

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
