package com.cpy.website.center.dao;

import com.cpy.website.api.param.QueryProjectParam;
import com.cpy.website.center.entity.CpyProject;

import java.util.List;

/**
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
public interface CpyProjectDao extends BaseDao<CpyProject> {

    List<CpyProject> queryListByParam(QueryProjectParam param);
}
