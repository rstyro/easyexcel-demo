package top.lrshuai.excel.exceltool.dropdown.annotation;

import top.lrshuai.excel.exceltool.dropdown.enums.DropDownEnum;
import top.lrshuai.excel.exceltool.dropdown.enums.DropDownType;
import top.lrshuai.excel.exceltool.dropdown.service.IDropDownService;

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
     * 指定提供下拉选项的枚举类
     * 该枚举必须实现 DropDownEnum 接口
     * @return
     */
    Class<? extends DropDownEnum> enumClass() default DropDownEnum.class;



    /**
     * 动态下拉内容，可查数据库返回等，其他操作
     * @return
     */
    Class<? extends IDropDownService> sourceClass() default IDropDownService.class;

    /**
     * 下拉类型，可能动态查询的时候需要用到
     * @return
     */
    DropDownType type() default DropDownType.NONE;

    /**
     * 传递给动态下拉服务的参数键值对
     * 用于更精细地控制动态下拉的数据获取
     * @return 参数数组，格式为 {"key1=value1", "key2=value2"}
     */
    String[] params() default {};

}
