package com.cpy.website.center.dao;

import com.cpy.website.api.param.QueryArticleParam;
import com.cpy.website.center.entity.CpyArticle;

import java.util.List;

/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyArticleDao extends BaseDao<CpyArticle> {

    List<CpyArticle> queryListByParam(QueryArticleParam param);
}
