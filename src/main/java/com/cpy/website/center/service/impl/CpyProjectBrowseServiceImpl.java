package com.cpy.website.center.service.impl;

import com.cpy.website.center.dao.CpyProjectBrowseDao;
import com.cpy.website.center.entity.CpyProjectBrowse;
import com.cpy.website.center.service.CpyProjectBrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyProjectBrowseServiceImpl implements CpyProjectBrowseService {
    @Autowired
    private CpyProjectBrowseDao cpyProjectBrowseDao;

    @Override
    public CpyProjectBrowse queryObject(Integer projectId) {
        return cpyProjectBrowseDao.queryObject(projectId);
    }

    @Override
    public List<CpyProjectBrowse> queryList(Map<String, Object> map) {
        return cpyProjectBrowseDao.queryList(map);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyProjectBrowse CpyProjectBrowse) throws Exception{
        CpyProjectBrowse.setCreateTime(new Date());
        CpyProjectBrowse.setUpdateTime(new Date());
            cpyProjectBrowseDao.save(CpyProjectBrowse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyProjectBrowse CpyProjectBrowse) throws Exception{
            cpyProjectBrowseDao.update(CpyProjectBrowse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer projectId) throws Exception{
            cpyProjectBrowseDao.delete(projectId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[]projectIds) throws Exception{
            cpyProjectBrowseDao.deleteBatch(projectIds);
    }

}
