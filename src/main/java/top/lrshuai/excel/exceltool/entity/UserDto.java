package top.lrshuai.excel.exceltool.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import top.lrshuai.excel.exceltool.converter.LocalDateTimeConverter;

import java.time.LocalDateTime;

@Data
public class UserDto {

    @ExcelProperty("用户名称")
    private String username;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("部门")
    private String department;

    @ExcelProperty("职业")
    private String occupation;

    @ExcelProperty(value = "注册时间",converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;
}
