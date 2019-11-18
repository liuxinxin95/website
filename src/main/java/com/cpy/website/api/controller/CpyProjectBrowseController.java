package com.cpy.website.api.controller;

import com.cpy.website.center.entity.CpyProjectBrowse;
import com.cpy.website.center.service.CpyProjectBrowseService;
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
 * 
 *
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("项目浏览")
@RestController
@RequestMapping("cpyprojectbrowse")
public class CpyProjectBrowseController {

    private static Logger log = LoggerFactory.getLogger(CpyProjectBrowseController.class);

    @Autowired
    private CpyProjectBrowseService cpyProjectBrowseService;

    /**
     * 列表
     */
    @ApiOperation("列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestBody Map<String, Object> params) {
        //查询列表数据
        PageInfo<CpyProjectBrowse> pageInfo = PageHelper.startPage(MapUtils.getInteger(params, "page"),
                MapUtils.getInteger(params, "limit")).doSelectPageInfo(() -> cpyProjectBrowseService.queryList(params));
        return Result.ok().put("page", pageInfo);
    }


    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/info/{projectId}",method = RequestMethod.GET)
    public Result info(@PathVariable("projectId") Integer projectId) {
            CpyProjectBrowse cpyProjectBrowse = cpyProjectBrowseService.queryObject(projectId);

        return Result.ok().put("cpyProjectBrowse", cpyProjectBrowse);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody CpyProjectBrowse cpyProjectBrowse) {
        try {
                cpyProjectBrowseService.save(cpyProjectBrowse);
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
    @RequestMapping(value = "/update" ,method = RequestMethod.PUT)
    public Result update(@RequestBody CpyProjectBrowse cpyProjectBrowse) {
        try {
                cpyProjectBrowseService.update(cpyProjectBrowse);
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
    @RequestMapping(value = "/delete" ,method = RequestMethod.DELETE)
    public Result delete(@RequestBody Integer[]projectIds) {
        try {
            if (projectIds.length == 1) {
                    cpyProjectBrowseService.delete(projectIds[0]);
            } else {
                    cpyProjectBrowseService.deleteBatch(projectIds);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return Result.error("删除异常");
        }
        return Result.ok();
    }

}
