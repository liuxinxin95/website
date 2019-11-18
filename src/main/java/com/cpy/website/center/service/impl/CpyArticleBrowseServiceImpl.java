package com.cpy.website.center.service.impl;

import com.cpy.website.center.dao.CpyArticleBrowseDao;
import com.cpy.website.center.entity.CpyArticleBrowse;
import com.cpy.website.center.service.CpyArticleBrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyArticleBrowseServiceImpl implements CpyArticleBrowseService {
    @Autowired
    private CpyArticleBrowseDao cpyArticleBrowseDao;

    @Override
    public CpyArticleBrowse queryObject(Integer articleId) {
        return cpyArticleBrowseDao.queryObject(articleId);
    }

    @Override
    public List<CpyArticleBrowse> queryList(Map<String, Object> map) {
        return cpyArticleBrowseDao.queryList(map);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyArticleBrowse cpyArticleBrowse) throws Exception{
        cpyArticleBrowse.setCreateTime(new Date());
        cpyArticleBrowse.setUpdateTime(new Date());
            cpyArticleBrowseDao.save(cpyArticleBrowse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyArticleBrowse CpyArticleBrowse) throws Exception{
            cpyArticleBrowseDao.update(CpyArticleBrowse);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer articleId) throws Exception{
            cpyArticleBrowseDao.delete(articleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[]articleIds) throws Exception{
            cpyArticleBrowseDao.deleteBatch(articleIds);
    }


}
