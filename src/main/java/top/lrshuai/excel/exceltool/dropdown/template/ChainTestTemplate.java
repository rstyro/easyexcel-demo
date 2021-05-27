package top.lrshuai.excel.exceltool.dropdown.template;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import top.lrshuai.excel.exceltool.dropdown.annotation.ChainDropDownFields;
import top.lrshuai.excel.exceltool.dropdown.enums.ChainDropDownType;
import top.lrshuai.excel.exceltool.dropdown.service.AreaChainDropDownService;
import top.lrshuai.excel.exceltool.dropdown.service.TestChainDropDownService;

@Data
// 内容行高度
@ContentRowHeight(20)
// 头部行高度
@HeadRowHeight(25)
// 列宽，可在类或属性中使用
@ColumnWidth(25)
public class ChainTestTemplate {

    @ExcelProperty("用户名称")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;


    @ExcelProperty("国家")
    @ChainDropDownFields(isRoot = true,sourceClass = TestChainDropDownService.class,type = ChainDropDownType.TEST)
    private String country;

    @ExcelProperty("省份")
    @ChainDropDownFields(sourceClass = TestChainDropDownService.class,type = ChainDropDownType.TEST,params = {"2"})
    private String province;

    @ExcelProperty("城市")
    @ChainDropDownFields(sourceClass = TestChainDropDownService.class,type = ChainDropDownType.TEST,params = {"3"})
    private String city;

    @ExcelProperty("区域")
    @ChainDropDownFields(sourceClass = TestChainDropDownService.class,type = ChainDropDownType.TEST,params = {"4"})
    private String zone;



    @ExcelProperty("省")
    @ChainDropDownFields(isRoot = true,sourceClass = AreaChainDropDownService.class,type = ChainDropDownType.AREA,params = {"1"})
    private String province2;

    @ExcelProperty("市")
    @ChainDropDownFields(sourceClass = AreaChainDropDownService.class,type = ChainDropDownType.AREA,params = {"2"})
    private String city2;

    @ExcelProperty("区")
    @ChainDropDownFields(sourceClass = AreaChainDropDownService.class,type = ChainDropDownType.AREA,params = {"3"})
    private String zone2;


}
