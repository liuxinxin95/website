package com.cpy.website.center.service;

import com.cpy.website.center.entity.CpyTag;

import java.util.List;
import java.util.Map;


/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyTagService {
    /**
     * 根据ID查询
     *
     * @return
     */
    CpyTag queryObject(Integer id);

    /**
     * 查询列表
     *
     * @param map
     * @return
     */
    List<CpyTag> queryList(Map<String, Object> map);

    /**
     * 保存
     *
     * @throws Exception
     */
    void save(CpyTag CpyTag) throws Exception;

    /**
     * 更新
     *
     * @throws Exception
     */
    void update(CpyTag CpyTag) throws Exception;

    /**
     * 删除
     *
     * @throws Exception
     */
    void delete(Integer id) throws Exception;

    /**
     * 批量删除
     *
     * @throws Exception
     */
    void deleteBatch(Integer[] ids) throws Exception;
}
