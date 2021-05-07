package top.lrshuai.excel.exceltool.dropdown.annotation;

import top.lrshuai.excel.exceltool.dropdown.enums.ChainDropDownType;

import java.lang.annotation.*;

/**
 * excel的级联下拉框注解
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChainDropDownFields {
    /**
     * 固定下拉
     * @return string[]
     */
    String[] source() default {};

    /**
     * 动态下拉内容，可查数据库返回等，其他操作
     * @return class[]
     */
    Class[] sourceClass() default {};

    /**
     * 获取下拉的参数,可以有1个到多个
     * @return string
     */
    String[] params() default {};

    /**
     * 级联下拉类型，可能动态查询的时候需要用到
     * @return type
     */
    ChainDropDownType type() default ChainDropDownType.NONE;


}
