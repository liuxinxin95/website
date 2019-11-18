package com.cpy.website.api.controller;

import com.cpy.website.api.param.QueryArticleParam;
import com.cpy.website.api.vo.CpyArticleVO;
import com.cpy.website.api.vo.CpyProjectVO;
import com.cpy.website.center.entity.CpyArticle;
import com.cpy.website.center.entity.CpyArticleBrowse;
import com.cpy.website.center.entity.CpyProjectBrowse;
import com.cpy.website.center.service.CpyArticleBrowseService;
import com.cpy.website.center.service.CpyArticleService;
import com.cpy.website.center.util.BeanMapUtil;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("文章")
@RestController
@RequestMapping("cpyarticle")
public class CpyArticleController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CpyArticleController.class);


    @Autowired
    private CpyArticleService cpyArticleService;

    @Resource
    private CpyArticleBrowseService cpyArticleBrowseService;

    /**
     * 列表
     */
    @ApiOperation("列表(后台)")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ApiResponse<PageInfo<CpyArticleVO>> list(@RequestBody QueryArticleParam param) {
        //查询列表数据
        PageInfo<CpyArticleVO> pageInfo = PageHelper.startPage(param.getPageNum(),
                param.getPageSize()).doSelectPageInfo(() -> BeanMapUtil.convertList(cpyArticleService.queryList(param), CpyArticleVO.class));
        List<CpyArticleVO> cpyArticleVOS = BeanMapUtil.convertList(pageInfo.getList(), CpyArticleVO.class);
        pageInfo.setList(cpyArticleVOS);

        pageInfo.getList().stream().forEach(x -> {
            CpyArticleBrowse cpyArticleBrowse = cpyArticleBrowseService.queryObject(x.getId());
            if (cpyArticleBrowse != null) {
                x.setBrowseNum(cpyArticleBrowse.getBrowseNum());
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
    public ApiResponse<CpyArticleVO> info(@PathVariable("id") Integer id) {
        CpyArticle cpyArticle = cpyArticleService.queryObject(id);
        CpyArticleVO cpyArticleVO = BeanMapUtil.convertObject(cpyArticle, CpyArticleVO.class);
        cpyArticleVO.setCategoryIdList(Arrays.asList(cpyArticle.getCategoryIds().split(",")));
        cpyArticleVO.setTagList(Arrays.asList(cpyArticle.getTags().split(",")));
        cpyArticleVO.setPicList(Arrays.asList(cpyArticle.getPics().split(",")));
        return success(cpyArticleVO);
    }

    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/articleDetail/{id}", method = RequestMethod.GET)
    public ApiResponse<CpyArticleVO> articleDetail(@PathVariable("id") Integer id) {
        CpyArticle cpyArticle = cpyArticleService.queryObject(id);
        CpyArticleBrowse cpyArticleBrowse = cpyArticleBrowseService.queryObject(cpyArticle.getId());
        cpyArticleBrowse.setBrowseNum(cpyArticleBrowse.getBrowseNum() + 1);
        try {
            cpyArticleBrowseService.update(cpyArticleBrowse);
        } catch (Exception e) {
            log.info("浏览量记录失败");
        }
        CpyArticleVO cpyArticleVO = BeanMapUtil.convertObject(cpyArticle, CpyArticleVO.class);
        cpyArticleVO.setCategoryIdList(Arrays.asList(cpyArticle.getCategoryIds().split(",")));
        cpyArticleVO.setTagList(Arrays.asList(cpyArticle.getTags().split(",")));
        cpyArticleVO.setPicList(Arrays.asList(cpyArticle.getPics().split(",")));
        return success(cpyArticleVO);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse<Boolean> save(@RequestBody CpyArticleVO cpyArticle) {
        try {
            CpyArticle cpyArticle1 = BeanMapUtil.convertObject(cpyArticle, CpyArticle.class);
            cpyArticle1.setCategoryIds(String.join(",", cpyArticle.getCategoryIdList()));
            cpyArticle1.setTags(String.join(",", cpyArticle.getTagList()));
            cpyArticle1.setPics(String.join(",", cpyArticle.getPicList()));
            cpyArticleService.save(cpyArticle1);
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
    public ApiResponse<Boolean> update(@RequestBody CpyArticleVO cpyArticle) {
        try {
            CpyArticle cpyArticle1 = BeanMapUtil.convertObject(cpyArticle, CpyArticle.class);
            cpyArticle1.setCategoryIds(String.join(",", cpyArticle.getCategoryIdList()));
            cpyArticle1.setTags(String.join(",", cpyArticle.getTagList()));
            cpyArticle1.setPics(String.join(",", cpyArticle.getPicList()));
            cpyArticleService.update(cpyArticle1);
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
                cpyArticleService.delete(ids[0]);
            } else {
                cpyArticleService.deleteBatch(ids);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return success(false);
        }

        return success(true);
    }

}
