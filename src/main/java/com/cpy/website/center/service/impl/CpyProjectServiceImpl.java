package com.cpy.website.center.service.impl;

import com.cpy.website.api.param.QueryProjectParam;
import com.cpy.website.center.dao.CpyProjectDao;
import com.cpy.website.center.entity.CpyProject;
import com.cpy.website.center.service.CpyProjectService;
import com.cpy.website.center.util.PinyinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Primary
public class CpyProjectServiceImpl implements CpyProjectService {
    @Autowired
    private CpyProjectDao cpyProjectDao;

    @Value("${project.package.path}")
    private String url;

    @Override
    public CpyProject queryObject(Integer id) {
        return cpyProjectDao.queryObject(id);
    }

    @Override
    public List<CpyProject> queryList(QueryProjectParam param) {
        return cpyProjectDao.queryListByParam(param);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CpyProject CpyProject) throws Exception {
        CpyProject.setCreateTime(new Date());
        CpyProject.setUpdateTime(new Date());
        cpyProjectDao.save(CpyProject);
        String pingYin = PinyinUtils.getPingYin(CpyProject.getTitle());
        File dir = new File(url+pingYin+"/");
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CpyProject CpyProject) throws Exception {
        cpyProjectDao.update(CpyProject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) throws Exception {
        cpyProjectDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] ids) throws Exception {
        cpyProjectDao.deleteBatch(ids);
    }

}
