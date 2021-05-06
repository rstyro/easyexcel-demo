package top.lrshuai.excel.exceltool.dropdown.annotation;

import top.lrshuai.excel.exceltool.dropdown.enums.DropDownType;

import java.lang.annotation.*;

/**
 * 导出excel的下拉框注解
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DropDownFields {
    /**
     * 固定下拉
     * @return
     */
    String[] source() default {};

    /**
     * 动态下拉内容，可查数据库返回等，其他操作
     * @return
     */
    Class[] sourceClass() default {};

    /**
     * 下拉类型，可能动态查询的时候需要用到
     * @return
     */
    DropDownType type() default DropDownType.NONE;

}
