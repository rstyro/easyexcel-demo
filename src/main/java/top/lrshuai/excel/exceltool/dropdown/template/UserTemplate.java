package top.lrshuai.excel.exceltool.dropdown.template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import top.lrshuai.excel.exceltool.converter.LocalDateTimeConverter;
import top.lrshuai.excel.exceltool.dropdown.annotation.DropDownFields;
import top.lrshuai.excel.exceltool.dropdown.enums.DropDownType;
import top.lrshuai.excel.exceltool.dropdown.service.OccupationDropDownService;

import java.time.LocalDateTime;

@Data
// 内容行高度
@ContentRowHeight(20)
// 头部行高度
@HeadRowHeight(25)
// 列宽，可在类或属性中使用
@ColumnWidth(25)
public class UserTemplate {

    @ExcelProperty("用户名称")
    private String username;

    @ExcelProperty("年龄")
    private Integer age;

    /**
     * 固定下拉
     */
    @ExcelProperty("部门")
    @DropDownFields(source = {"财务部","人事部","研发部","商务部"})
    private String department;

    /**
     * 下拉枚举
     */
    @ExcelProperty("类型")
    @DropDownFields(enumClass = DropDownType.class)
    private String type;

    /**
     * 动态下拉
     */
    @ExcelProperty("职业")
    @DropDownFields(sourceClass = OccupationDropDownService.class,type = DropDownType.OCCUPATION)
    private String occupation;

    /**
     * 动态下拉
     */
    @ExcelProperty("职业2")
    @DropDownFields(sourceClass = OccupationDropDownService.class,params = {"type=occupation"})
    private String occupation2;

    @ColumnWidth(50)
    @ExcelProperty(value = "注册时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime=LocalDateTime.now();
}
