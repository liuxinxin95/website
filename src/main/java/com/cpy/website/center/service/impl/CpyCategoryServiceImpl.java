package com.cpy.website.center.service.impl;

import com.cpy.website.center.dao.CpyCategoryDao;
import com.cpy.website.center.entity.CpyCategory;
import com.cpy.website.center.service.CpyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyCategoryServiceImpl implements CpyCategoryService {

    @Resource
    private CpyCategoryDao cpyCategoryDao;

    @Override
    public CpyCategory queryObject(Integer id) {
        return cpyCategoryDao.queryObject(id);
    }

    @Override
    public List<CpyCategory> queryList(Map<String, Object> map) {
        return cpyCategoryDao.queryList(map);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyCategory CpyCategory) throws Exception{
        CpyCategory.setCreateTime(new Date());
        CpyCategory.setUpdateTime(new Date());
            cpyCategoryDao.save(CpyCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyCategory CpyCategory) throws Exception{
            cpyCategoryDao.update(CpyCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception{
            cpyCategoryDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[]ids) throws Exception{
            cpyCategoryDao.deleteBatch(ids);
    }

}
