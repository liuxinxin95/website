package com.cpy.website.center.service;

import com.cpy.website.center.entity.CpyArticleBrowse;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyArticleBrowseService {
    /**
    * 根据ID查询
    * @return
    */
     CpyArticleBrowse queryObject(Integer articleId);

    /**
    * 查询列表
    * @param map
    * @return
    */
    List<CpyArticleBrowse> queryList(Map<String, Object> map);

    /**
    * 保存
    * @throws Exception
    */
    void save(CpyArticleBrowse CpyArticleBrowse) throws Exception;

    /**
    * 更新
    * @throws Exception
    */
    void update(CpyArticleBrowse CpyArticleBrowse) throws Exception;

    /**
    * 删除
    * @throws Exception
    */
    void delete(Integer articleId) throws Exception;

    /**
    * 批量删除
    * @throws Exception
    */
    void deleteBatch(Integer[] articleIds) throws Exception;
}
