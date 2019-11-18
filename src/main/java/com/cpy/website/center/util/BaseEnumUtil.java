package com.cpy.website.center.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 枚举工具类
 *
 * @author 蔡崇建
 */
public class BaseEnumUtil {

    private BaseEnumUtil() {
    }

    /**
     * 根据code获取枚举
     *
     * @param code     代码
     * @param enumType 枚举类型对象
     * @param <T>      枚举类型
     * @return 枚举
     */
    public static <T extends IBaseEnum> T getEnumByCode(Object code, Class<T> enumType) {
        if (code != null) {
            for (T t : enumType.getEnumConstants()) {
                if (StringUtils.equals(String.valueOf(t.getCode()), String.valueOf(code))) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * 根据desc获取枚举
     *
     * @param desc     描述
     * @param enumType 枚举类型对象
     * @param <T>      枚举类型
     * @return 枚举
     */
    public static <T extends IBaseEnum> T getEnumByDesc(String desc, Class<T> enumType) {
        if (StringUtils.isNotBlank(desc)) {
            for (T t : enumType.getEnumConstants()) {
                if (t.getDesc().equals(desc)) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * 根据code或者desc获取枚举
     *
     * @param code     代码或者描述
     * @param enumType 枚举类型对象
     * @param <T>      枚举类型
     * @return 枚举
     */
    public static <T extends IBaseEnum> T getEnumByCodeOrDesc(Object code, Class<T> enumType) {
        T result = getEnumByCode(code, enumType);
        if (result == null) {
            result = getEnumByDesc(ObjectUtils.toString(code), enumType);
        }
        return result;
    }
}
