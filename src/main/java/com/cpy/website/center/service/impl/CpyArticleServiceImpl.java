package com.cpy.website.center.service.impl;

import com.cpy.website.api.param.QueryArticleParam;
import com.cpy.website.center.dao.CpyArticleDao;
import com.cpy.website.center.entity.CpyArticle;
import com.cpy.website.center.service.CpyArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyArticleServiceImpl implements CpyArticleService {
    @Autowired
    private CpyArticleDao cpyArticleDao;

    @Override
    public CpyArticle queryObject(Integer id) {
        return cpyArticleDao.queryObject(id);
    }

    @Override
    public List<CpyArticle> queryList(QueryArticleParam param) {
        return cpyArticleDao.queryListByParam(param);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyArticle CpyArticle) throws Exception{
        CpyArticle.setCreateTime(new Date());
        CpyArticle.setUpdateTime(new Date());
            cpyArticleDao.save(CpyArticle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyArticle CpyArticle) throws Exception{
            cpyArticleDao.update(CpyArticle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception{
            cpyArticleDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[]ids) throws Exception{
            cpyArticleDao.deleteBatch(ids);
    }

}
