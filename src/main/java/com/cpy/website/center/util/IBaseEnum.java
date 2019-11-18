package com.cpy.website.center.util;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 实现此接口的枚举类型能被Spring MVC接收参数时自动转换
 * @author 蔡崇建
 * @param <T> 代码类型
 */
public interface IBaseEnum<T> extends Serializable {

    /**
     * 返回枚举代码
     * @return 枚举代码
     */
    T getCode();

    /**
     * 返回枚举描述
     * @return 返回枚举描述
     */
    @JsonValue
    String getDesc();
}
