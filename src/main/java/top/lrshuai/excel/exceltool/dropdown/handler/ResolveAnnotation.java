package top.lrshuai.excel.exceltool.dropdown.handler;

import top.lrshuai.excel.exceltool.dropdown.annotation.ChainDropDownFields;
import top.lrshuai.excel.exceltool.dropdown.annotation.DropDownFields;
import top.lrshuai.excel.exceltool.dropdown.service.IChainDropDownService;
import top.lrshuai.excel.exceltool.dropdown.service.IDropDownService;
import top.lrshuai.excel.exceltool.entity.ChainDropDown;

import java.util.Arrays;
import java.util.Optional;

/**
 * 解析下拉注解工具类
 */
public class ResolveAnnotation {

    /**
     * 解析注解，得到具体的下拉数据
     * @param dropDownField 注解
     * @return string[]
     */
    public static String[] resolve(DropDownFields dropDownField) {
        if (!Optional.ofNullable(dropDownField).isPresent()) {
            return new String[0];
        }
        // 获取固定下拉信息
        String[] source = dropDownField.source();
        if (source.length > 0) {
            return source;
        }

        // 获取动态的下拉数据
        Class<? extends IDropDownService>[] classes = dropDownField.sourceClass();
        if (null != classes && classes.length > 0) {
            try {
                IDropDownService dropDownService = Arrays.stream(classes).findFirst().get().newInstance();
                String[] dynamicSource = dropDownService.getSource(dropDownField.type().getValue());
                if (null != dynamicSource && dynamicSource.length > 0) {
                    return dynamicSource;
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
                ChainDropDown source = chainDropDownService.getSource(chainDropDownFields.isRoot(),chainDropDownFields.params());
                source.setTypeName(chainDropDownFields.type().getValue());
                return source;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
