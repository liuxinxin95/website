package com.cpy.website.api.param;

import com.cpy.website.center.util.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Carl
 * @ClassName QueryArticleParam
 * @Description
 * @date 2019-11-14 22:04
 **/
@ApiModel
@Data
public class QueryArticleParam extends PageRequest {

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;


    @ApiModelProperty(value = "状态：0待审核1已发布")
    private Integer status;

    @ApiModelProperty(value = "名称")
    private String title;
}
