package top.lrshuai.excel.exceltool.dropdown.handler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import top.lrshuai.excel.exceltool.dropdown.annotation.ChainDropDownFields;
import top.lrshuai.excel.exceltool.dropdown.annotation.DropDownFields;
import top.lrshuai.excel.exceltool.dropdown.enums.DropDownEnum;
import top.lrshuai.excel.exceltool.dropdown.enums.DropDownType;
import top.lrshuai.excel.exceltool.dropdown.service.IChainDropDownService;
import top.lrshuai.excel.exceltool.dropdown.service.IDropDownService;
import top.lrshuai.excel.exceltool.entity.ChainDropDown;

import java.util.*;

/**
 * 解析下拉注解工具类
 */
@Slf4j
public class ResolveAnnotation {

    /**
     * 解析注解，得到具体的下拉数据
     *
     * @param dropDownField 注解
     * @return string[]
     */
    @SneakyThrows
    public static String[] resolve(DropDownFields dropDownField) {
        if (dropDownField == null) {
            return new String[0];
        }
        // 获取固定下拉信息
        String[] source = dropDownField.source();
        if (source.length > 0) {
            return source;
        }
        if (!dropDownField.enumClass().equals(DropDownEnum.class)) {
            // 通过枚举类获取选项
            DropDownEnum[] enumConstants = dropDownField.enumClass().getEnumConstants();
            if (enumConstants != null) { // 安全获取枚举常量
                return Arrays.stream(enumConstants)
                        .map(DropDownEnum::getDropDownValue)
                        .toArray(String[]::new);
            }
        }

        // 获取动态的下拉数据
        try {
            IDropDownService dropDownService = dropDownField.sourceClass().newInstance();
            if(!ObjectUtils.isEmpty(dropDownField.params())){
                source=dropDownService.getSource(dropDownField.params());
            }else if(!DropDownType.NONE.equals(dropDownField.type())){
                source=dropDownService.getSource(dropDownField.type().getValue());
            }
            if (null != source && source.length > 0) {
                return source;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new String[0];
    }

    public static ChainDropDown resolve(ChainDropDownFields chainDropDownFields) {
        if (!Optional.ofNullable(chainDropDownFields).isPresent()) {
            return null;
        }
        // 获取动态的下拉数据
        Class<? extends IChainDropDownService>[] classes = chainDropDownFields.sourceClass();
        if (null != classes && classes.length > 0) {
            try {
                IChainDropDownService chainDropDownService = Arrays.stream(classes).findFirst().get().newInstance();
                ChainDropDown source = chainDropDownService.getSource(chainDropDownFields.isRoot(), chainDropDownFields.params());
                source.setTypeName(chainDropDownFields.type().getValue());
                return source;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
