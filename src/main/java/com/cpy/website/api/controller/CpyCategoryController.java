package com.cpy.website.api.controller;

import com.cpy.website.api.vo.CpyCategoryVO;
import com.cpy.website.api.vo.CpyTagVO;
import com.cpy.website.center.entity.CpyCategory;
import com.cpy.website.center.entity.CpyTag;
import com.cpy.website.center.service.CpyCategoryService;
import com.cpy.website.center.util.BeanMapUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxinxin
 * @email 18634318474@163.com
 * @date 2019-11-13
 */
@Api("分类")
@RestController
@RequestMapping("cpycategory")
public class CpyCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CpyCategoryController.class);
    @Autowired

    @Resource
    private CpyCategoryService cpyCategoryService;


    /**
     * 所有列表
     */
    @ApiOperation("文章分类所有列表")
    @RequestMapping(value = "/articleAllList", method = RequestMethod.POST)
    public ApiResponse<List<CpyCategoryVO>> articleAllList() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("type", 0);
        //查询列表数据
        List<CpyCategory> cpyTags = cpyCategoryService.queryList(objectObjectHashMap);
        return success(BeanMapUtil.convertList(cpyTags, CpyCategoryVO.class));
    }

    /**
     * 所有列表
     */
    @ApiOperation("项目所有列表")
    @RequestMapping(value = "/projectAllList", method = RequestMethod.POST)
    public ApiResponse<List<CpyCategoryVO>> projectAllList() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("type", 1);
        //查询列表数据
        List<CpyCategory> cpyTags = cpyCategoryService.queryList(objectObjectHashMap);
        return success(BeanMapUtil.convertList(cpyTags, CpyCategoryVO.class));
    }

    /**
     * 信息
     */
    @ApiOperation("信息")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ApiResponse<CpyCategoryVO> info(@PathVariable("id") Integer id) {
        CpyCategory cpyCategory = cpyCategoryService.queryObject(id);
        return success(BeanMapUtil.convertObject(cpyCategory, CpyCategoryVO.class));
    }

    /**
     * 保存
     */
    @ApiOperation("文章分类保存")
    @RequestMapping(value = "/articleSave", method = RequestMethod.POST)
    public ApiResponse<Boolean> articleSave(@RequestBody CpyCategoryVO cpyCategory) {
        try {
            CpyCategory cpyCategory1 = BeanMapUtil.convertObject(cpyCategory, CpyCategory.class);
            cpyCategory1.setType(0);
            cpyCategoryService.save(cpyCategory1);
        } catch (Exception e) {
            log.error("保存异常", e);
            return success(false);
        }

        return success(true);
    }

    /**
     * 保存
     */
    @ApiOperation("项目分类保存")
    @RequestMapping(value = "/projectSave", method = RequestMethod.POST)
    public ApiResponse<Boolean> projectSave(@RequestBody CpyCategoryVO cpyCategory) {
        try {
            CpyCategory cpyCategory1 = BeanMapUtil.convertObject(cpyCategory, CpyCategory.class);
            cpyCategory1.setType(1);
            cpyCategoryService.save(cpyCategory1);
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
    public ApiResponse<Boolean> update(@RequestBody CpyCategoryVO cpyCategory) {
        try {
            cpyCategoryService.update(BeanMapUtil.convertObject(cpyCategory, CpyCategory.class));
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
                cpyCategoryService.delete(ids[0]);
            } else {
                cpyCategoryService.deleteBatch(ids);
            }
        } catch (Exception e) {
            log.error("删除异常", e);
            return success(false);
        }
        return success(true);
    }

}
