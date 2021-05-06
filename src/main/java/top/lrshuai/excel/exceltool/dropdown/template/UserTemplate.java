package top.lrshuai.excel.exceltool.dropdown.template;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import top.lrshuai.excel.exceltool.dropdown.annotation.DropDownFields;
import top.lrshuai.excel.exceltool.dropdown.enums.DropDownType;
import top.lrshuai.excel.exceltool.dropdown.service.OccupationDropDownService;

@Data
public class UserTemplate {

    @ExcelProperty("用户名称")
    private String username;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("部门")
    @DropDownFields(source = {"财务部","人事部","研发部","商务部"})
    private String department;

    @ExcelProperty("职业")
    @DropDownFields(sourceClass = OccupationDropDownService.class,type = DropDownType.OCCUPATION)
    private String occupation;
}
