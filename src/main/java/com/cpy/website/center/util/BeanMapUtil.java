package com.cpy.website.center.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于Bean映射的工具类，
 * 因为Spring的BeanUtils只能映射类型相同的属性，无法满足将Date转字符串，枚举代码转枚举类型等需求，
 * 因此将Spring的BeanUtils源码复制出来，自己扩展了映射逻辑
 *
 * @author 蔡崇建
 */
public class BeanMapUtil {

    /**
     * 缓存字段上使用DateFormat注解配置的日期格式，key为包名+类名+字段名，value为使用DateFormat注解配置的日期格式
     */
    private static final Map<String, String> DATE_FORMAT_CACHE = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanMapUtil.class);

    private BeanMapUtil() {
    }

    /**
     * <p>将集合转换成其他类型的集合，并将每个元素的属性根据名称拷贝到新集合的元素中。如果遇到属性类型不一致，会自动根据类型进行转换</p>
     * <p>通常用于将pojo转成VO，例如：</p>
     * <p>List&lt;UserVO&gt; userVOList = CollectionUtil.convertList(userList, UserVO.class);</p>
     * <p>表示将userList转成元素类型为UserVO的集合</p>
     * <p>在转换时，如果字段同名但类型不一致，还支持日期类型自动转成yyyy-MM-dd HH:mm:ss类型的字符串、yyyy-MM-dd HH:mm:ss格式的字符串字段转成日期类型，将枚举代码转换为枚举类型、将所有基本数据类型自动转为字符串类型功能</p>
     *
     * @param originalList 原始的集合
     * @param resultType   新集合元素的类对象
     * @param <T>          新集合元素的类型
     * @return 新集合
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> convertList(List<?> originalList, Class<T> resultType) {
        return convertList(originalList, resultType, (String[]) null);
    }

    /**
     * <p>将集合转换成其他类型的集合，并将每个元素的属性根据名称拷贝到新集合的元素中。如果遇到属性类型不一致，会自动根据类型进行转换</p>
     * <p>通常用于将pojo转成VO，例如：</p>
     * <p>List&lt;UserVO&gt; userVOList = CollectionUtil.convertList(userList, UserVO.class);</p>
     * <p>表示将userList转成元素类型为UserVO的集合</p>
     * <p>在转换时，如果字段同名但类型不一致，还支持日期类型自动转成yyyy-MM-dd HH:mm:ss类型的字符串、yyyy-MM-dd HH:mm:ss格式的字符串字段转成日期类型，将枚举代码转换为枚举类型、将所有基本数据类型自动转为字符串类型功能</p>
     *
     * @param originalList     原始的集合
     * @param resultType       新集合元素的类对象
     * @param ignoreProperties array of property names to ignore
     * @param <T>              新集合元素的类型
     * @return 新集合
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> convertList(List<?> originalList, Class<T> resultType,
                                          @Nullable String... ignoreProperties) {
        if (CollectionUtils.isEmpty(originalList)) {
            return Lists.newArrayList();
        }

        // 类型相同，直接返回，不再通过反射复制属性
        if (resultType.isAssignableFrom(originalList.get(0).getClass())) {
            return (List<T>) originalList;
        }

        // 将集合转换成其他类型的集合，并将每个元素的属性根据名称拷贝到新集合的元素中
        List<T> result = new ArrayList<>(originalList.size());
        for (Object o : originalList) {
            T newObject = BeanUtils.instantiateClass(resultType);
            copyProperties(o, newObject, ignoreProperties);
            result.add(newObject);
        }
        return result;
    }

    /**
     * 将对象转换成其他类型的对象，并将属性根据名称拷贝到新对象。如果遇到属性类型不一致，会自动根据类型进行转换
     * 在转换时，如果字段同名但类型不一致，还支持日期类型自动转成yyyy-MM-dd HH:mm:ss类型的字符串、yyyy-MM-dd HH:mm:ss格式的字符串字段转成日期类型，将枚举代码转换为枚举类型、将所有基本数据类型自动转为字符串类型功能
     *
     * @param source     原始对象
     * @param resultType 目标类型的类对象
     * @param <T>        目标类型
     * @return 新对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertObject(Object source, Class<T> resultType) {
        return convertObject(source, resultType, (String[]) null);
    }

    /**
     * 将对象转换成其他类型的对象，并将属性根据名称拷贝到新对象。如果遇到属性类型不一致，会自动根据类型进行转换
     * 在转换时，如果字段同名但类型不一致，还支持日期类型自动转成yyyy-MM-dd HH:mm:ss类型的字符串、yyyy-MM-dd HH:mm:ss格式的字符串字段转成日期类型，将枚举代码转换为枚举类型、将所有基本数据类型自动转为字符串类型功能
     *
     * @param source           原始对象
     * @param resultType       目标类型的类对象
     * @param ignoreProperties array of property names to ignore
     * @param <T>              目标类型
     * @return 新对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T convertObject(Object source, Class<T> resultType,
                                      @Nullable String... ignoreProperties) {
        if (source == null) {
            return null;
        }

        // 类型相同，直接返回，不再通过反射复制属性
        if (resultType.isAssignableFrom(source.getClass())) {
            return (T) source;
        }

        // 转换类型
        T result = BeanUtils.instantiateClass(resultType);
        copyProperties(source, result, ignoreProperties);
        return result;
    }

    /**
     * Copy the property values of the given source bean into the target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     * <p>This is just a convenience method. For more complex transfer needs,
     * consider using a full BeanWrapper.
     *
     * @param source the source bean
     * @param target the target bean
     * @see BeanWrapper
     */
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, (String[]) null);
    }

    /**
     * 将Map转成javabean
     *
     * @param map       map
     * @param beanClass 目标类型的类对象
     * @param <T>       目标类型
     * @return javabean
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
        if (map == null) {
            return null;
        }

        T obj;
        try {
            obj = beanClass.newInstance();

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(obj, map.get(property.getName()));
                }
            }
        } catch (IllegalAccessException | IntrospectionException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(
                    "Could not transform " + map + " to " + beanClass.getName(), e);
        }

        return obj;
    }

    /**
     * 将javabean转成Map
     *
     * @param obj javabean
     * @return 转换结果
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        }

        Map<String, Object> map = new HashMap<>(32);

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            throw new IllegalStateException(
                    "Could not transform " + JSON.toJSONString(obj) + " to map", e);
        }

        return map;
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>Note: The source and target classes do not have to match or even be derived
     * from each other, as long as the properties match. Any bean properties that the
     * source bean exposes but the target bean does not will silently be ignored.
     *
     * @param source           the source bean
     * @param target           the target bean
     * @param ignoreProperties array of property names to ignore
     * @see BeanWrapper
     */
    public static void copyProperties(Object source, Object target,
                                      @Nullable String... ignoreProperties) {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> targetClass = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(targetClass);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object sourceValue = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            if (sourceValue != null) {
                                Object targetValue = getTargetValue(targetPd, sourceValue, targetClass);
                                if (targetValue != null) {
                                    writeMethod.invoke(target, targetValue);
                                }
                            }
                        } catch (Exception ex) {
                            throw new IllegalStateException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

    /**
     * 当源属性类型和目标属性类型不同时，进行一些内置的转换
     */
    @SuppressWarnings("unchecked")
    private static Object getTargetValue(PropertyDescriptor targetPd, Object sourceValue, Class<?> targetClass) throws ParseException {
        Object targetValue = sourceValue;
        Class<?> targetPropertyType = targetPd.getPropertyType();
        Class<?> sourcePropertyType = sourceValue.getClass();
        // 拷贝属性时如果源类型和目标类型不一致，如果是预定义好的可转换的情况，进行内置转换
        if (!targetPropertyType.isAssignableFrom(sourcePropertyType)) {
            // 将日期类型自动转成yyyy-MM-dd HH:mm:ss类型的字符串
            if (Date.class.isAssignableFrom(sourcePropertyType) && String.class.isAssignableFrom(targetPropertyType)) {
                targetValue = DateFormatUtils.format((Date) sourceValue, getDateFormat(targetPd.getName(), targetClass));
            }
            // 将yyyy-MM-dd HH:mm:ss类型的字符串自动转成日期类型
            else if (String.class.isAssignableFrom(sourcePropertyType) && Date.class.isAssignableFrom(targetPropertyType)) {
                targetValue = DateUtils.parseDate((String) sourceValue, getDateFormat(targetPd.getName(), targetClass));
            }
            // 将枚举代码转换为枚举类型
            else if (IBaseEnum.class.isAssignableFrom(targetPropertyType)) {
                targetValue = BaseEnumUtil.getEnumByCode(sourceValue, (Class<? extends IBaseEnum>) targetPropertyType);
            }
            // 将枚举类型转换为枚举代码
            else if (IBaseEnum.class.isAssignableFrom(sourcePropertyType)) {
                IBaseEnum baseEnum = (IBaseEnum) sourceValue;
                targetValue = baseEnum.getCode().getClass().isAssignableFrom(targetPropertyType) ? baseEnum.getCode() : baseEnum.getDesc();
            }
            // 目标类型指定为String，而源类型不是String的，调用String.valueOf()方法进行适配转换
            else if (String.class.isAssignableFrom(targetPropertyType)) {
                targetValue = String.valueOf(sourceValue);
            }
        }
        return targetValue;
    }

    private static String getDateFormat(String targetPropertyName, Class<?> targetClass) {

        String key = targetClass.getName() + "-" + targetPropertyName;
        return DATE_FORMAT_CACHE.computeIfAbsent(key, o -> {
            DateFormat annotation = null;
            try {
                Field field = targetClass.getDeclaredField(targetPropertyName);
                annotation = field.getAnnotation(DateFormat.class);
            } catch (NoSuchFieldException e) {
                LOGGER.error("no such field", e);
            }
            return Optional.ofNullable(annotation).map(DateFormat::value).orElse(DateUtil.DEFAULT_DATE_FORMAT);
        });
    }
}
