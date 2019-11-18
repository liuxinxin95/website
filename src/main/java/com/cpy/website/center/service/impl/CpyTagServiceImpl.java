package com.cpy.website.center.service.impl;

import com.cpy.website.center.dao.CpyTagDao;
import com.cpy.website.center.entity.CpyTag;
import com.cpy.website.center.service.CpyTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyTagServiceImpl implements CpyTagService {
    @Autowired
    private CpyTagDao cpyTagDao;

    @Override
    public CpyTag queryObject(Integer id) {
        return cpyTagDao.queryObject(id);
    }

    @Override
    public List<CpyTag> queryList(Map<String, Object> map) {
        return cpyTagDao.queryList(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyTag CpyTag) throws Exception{
        CpyTag.setCreateTime(new Date());
        CpyTag.setUpdateTime(new Date());
            cpyTagDao.save(CpyTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyTag CpyTag) throws Exception{
            cpyTagDao.update(CpyTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception{
            cpyTagDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[]ids) throws Exception{
            cpyTagDao.deleteBatch(ids);
    }

}
