package com.cpy.website.api.controller;

import com.cpy.website.api.param.QueryProjectParam;
import com.cpy.website.api.vo.CpyArticleVO;
import com.cpy.website.api.vo.CpyProjectVO;
import com.cpy.website.center.entity.CpyArticleBrowse;
import com.cpy.website.center.entity.CpyProject;
import com.cpy.website.center.entity.CpyProjectBrowse;
import com.cpy.website.center.service.CpyProjectBrowseService;
import com.cpy.website.center.service.CpyProjectService;
import com.cpy.website.center.util.BeanMapUtil;
import com.cpy.website.center.util.PinyinUtils;
import com.cpy.website.center.util.Result;
import com.cpy.website.framework.ApiResponse;
import com.cpy.website.framework.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("项目")
@RestController
@RequestMapping("cpyproject")
public class CpyProjectController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CpyProjectController.class);


    @Autowired
    private CpyProjectService cpyProjectService;

    @Resource
    private CpyProjectBrowseService cpyProjectBrowseService;

    /**
     * 列表
     */
    @ApiOperation("列表(后台)")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiResponse<PageInfo<CpyProjectVO>> list(@RequestBody QueryProjectParam param) {
        //查询列表数据
        PageInfo<CpyProjectVO> pageInfo = PageHelper.startPage(param.getPageNum(),
                param.getPageSize()).doSelectPageInfo(() -> cpyProjectService.queryList(param));
        List<CpyProjectVO> cpyProjectVOS = BeanMapUtil.convertList(pageInfo.getList(), CpyProjectVO.class);
        pageInfo.setList(cpyProjectVOS);

        pageInfo.getList().stream().forEach(x -> {
            CpyProjectBrowse cpyProjectBrowse = cpyProjectBrowseService.queryObject(x.getId());
            if (cpyProjectBrowse != null) {
                x.setBrowseNum(cpyProjectBrowse.getBrowseNum());
            } else {
                x.setBrowseNum(0);
            }
        });

        return success(pageInfo);
    }


    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ApiResponse<CpyProjectVO> info(@PathVariable("id") Integer id) {
        CpyProject cpyProject = cpyProjectService.queryObject(id);
        CpyProjectVO cpyProjectVO = BeanMapUtil.convertObject(cpyProject, CpyProjectVO.class);
        cpyProjectVO.setCategoryIdList(Arrays.asList(cpyProject.getCategoryIds().split(",")));
        cpyProjectVO.setPicList(Arrays.asList(cpyProject.getPics().split(",")));
        return success(cpyProjectVO);
    }


    /**
     * 信息
     */
    @ApiOperation("信息(企业站页面查询)")
    @RequestMapping(value = "/projectDetail/{id}", method = RequestMethod.GET)
    public ApiResponse<CpyProjectVO> projectDetail(@PathVariable("id") Integer id) {
        CpyProject cpyProject = cpyProjectService.queryObject(id);
        CpyProjectBrowse cpyProjectBrowse = cpyProjectBrowseService.queryObject(cpyProject.getId());
        cpyProjectBrowse.setBrowseNum(cpyProjectBrowse.getBrowseNum()+1);
        try {
            cpyProjectBrowseService.update(cpyProjectBrowse);
        } catch (Exception e) {
            log.info("浏览量记录失败");
        }
        CpyProjectVO cpyProjectVO = BeanMapUtil.convertObject(cpyProject, CpyProjectVO.class);
        cpyProjectVO.setCategoryIdList(Arrays.asList(cpyProject.getCategoryIds().split(",")));
        cpyProjectVO.setPicList(Arrays.asList(cpyProject.getPics().split(",")));
        return success(cpyProjectVO);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse<Boolean> save(@RequestBody CpyProjectVO cpyProject) {
        try {
            CpyProject cpyProject1 = BeanMapUtil.convertObject(cpyProject, CpyProject.class);
            cpyProject1.setStatus(0);
            cpyProject1.setCategoryIds(String.join(",", cpyProject.getCategoryIdList()));
            cpyProject1.setPics(String.join(",", cpyProject.getPicList()));
            cpyProjectService.save(cpyProject1);



        } catch (Exception e) {
            log.error("保存异常", e);
            return success(false);
        }
        return success(true);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ApiResponse<Boolean> update(@RequestBody CpyProjectVO cpyProject) {
        try {
            CpyProject cpyProject1 = BeanMapUtil.convertObject(cpyProject, CpyProject.class);
            cpyProject1.setCategoryIds(String.join(",", cpyProject.getCategoryIdList()));
            cpyProject1.setPics(String.join(",", cpyProject.getPicList()));
            cpyProjectService.update(cpyProject1);
        } catch (Exception e) {
            log.error("修改异常", e);
            return success(false);
        }
        return success(true);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ApiResponse<Boolean> delete(@RequestBody Integer[] ids) {
        try {
            if (ids.length == 1) {
                cpyProjectService.delete(ids[0]);
            } else {
                cpyProjectService.deleteBatch(ids);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return success(false);
        }
        return success(true);
    }

}
