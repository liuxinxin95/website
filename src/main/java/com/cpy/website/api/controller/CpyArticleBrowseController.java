package com.cpy.website.api.controller;

import com.cpy.website.center.entity.CpyArticleBrowse;
import com.cpy.website.center.service.CpyArticleBrowseService;
import com.cpy.website.center.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("文章浏览")
@RestController
@RequestMapping("cpyarticlebrowse")
public class CpyArticleBrowseController {

    private static Logger log = LoggerFactory.getLogger(CpyArticleBrowseController.class);

    @Autowired
    private CpyArticleBrowseService cpyArticleBrowseService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        PageInfo<CpyArticleBrowse> pageInfo = PageHelper.startPage(MapUtils.getInteger(params, "page"),
                MapUtils.getInteger(params, "limit")).doSelectPageInfo(() -> cpyArticleBrowseService.queryList(params));
        return Result.ok().put("page", pageInfo);
    }


    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/info/{articleId}", method = RequestMethod.GET)
    public Result info(@PathVariable("articleId") Integer articleId) {
        CpyArticleBrowse cpyArticleBrowse = cpyArticleBrowseService.queryObject(articleId);

        return Result.ok().put("cpyArticleBrowse", cpyArticleBrowse);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody CpyArticleBrowse cpyArticleBrowse) {
        try {
            cpyArticleBrowseService.save(cpyArticleBrowse);
        } catch (Exception e) {
            log.error("保存异常", e);
            return Result.error("保存异常");
        }

        return Result.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody CpyArticleBrowse cpyArticleBrowse) {
        try {
            cpyArticleBrowseService.update(cpyArticleBrowse);
        } catch (Exception e) {
            log.error("修改异常", e);
            return Result.error("修改异常");
        }
        return Result.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete(@RequestBody Integer[] articleIds) {
        try {
            if (articleIds.length == 1) {
                cpyArticleBrowseService.delete(articleIds[0]);
            } else {
                cpyArticleBrowseService.deleteBatch(articleIds);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return Result.error("删除异常");
        }
        return Result.ok();
    }

}
