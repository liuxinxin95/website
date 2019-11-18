package com.cpy.website.api.controller;

import com.cpy.website.api.vo.CpyTagVO;
import com.cpy.website.center.entity.CpyTag;
import com.cpy.website.center.service.CpyTagService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("标签")
@RestController
@RequestMapping("cpytag")
public class CpyTagController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CpyTagController.class);

    @Autowired
    private CpyTagService cpyTagService;


    /**
     * 所有列表
     */
    @ApiOperation("所有列表")
    @RequestMapping(value = "/allList", method = RequestMethod.POST)
    public ApiResponse<List<CpyTagVO>> list() {
        //查询列表数据
        List<CpyTag> cpyTags = cpyTagService.queryList(new HashMap<>());
        return success(BeanMapUtil.convertList(cpyTags, CpyTagVO.class));
    }


    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ApiResponse<CpyTagVO> info(@PathVariable("id") Integer id) {
        CpyTag cpyTag = cpyTagService.queryObject(id);
        return success(BeanMapUtil.convertObject(cpyTag, CpyTagVO.class));
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResponse save(@RequestBody CpyTagVO cpyTag) {
        try {
            cpyTagService.save(BeanMapUtil.convertObject(cpyTag, CpyTag.class));
        } catch (Exception e) {
            return success(false);
        }
        return success(true);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ApiResponse update(@RequestBody CpyTag cpyTag) {
        try {
            cpyTagService.update(cpyTag);
        } catch (Exception e) {
            log.error("修改异常", e);
            success(false);
        }
        return success(true);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ApiResponse delete(@RequestBody Integer[] ids) {
        try {
            if (ids.length == 1) {
                cpyTagService.delete(ids[0]);
            } else {
                cpyTagService.deleteBatch(ids);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return success(false);
        }
        return success(true);
    }

}
