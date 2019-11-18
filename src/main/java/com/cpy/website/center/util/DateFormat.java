package com.cpy.website.center.util;

import java.lang.annotation.*;

/**
 * 在使用BeanMapUtil进行Bean映射时，如果需要指定日期转换或者格式化的字符串格式，可以使用此注解指定。
 * 具体使用方法为在目标类的日期字段或者日期字符串字段上加上此注解，并指定日期格式。
 * BeanMapUtil进行Bean映射时如果检测到目标类的日期字段上指定了日期格式，则会使用指定的格式进行转换或者格式化
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormat {

    /**
     * 指定日期格式
     * @return 日期格式
     */
    String value() default DateUtil.DEFAULT_DATE_FORMAT;
}
