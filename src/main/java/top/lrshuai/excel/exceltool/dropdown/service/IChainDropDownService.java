package top.lrshuai.excel.exceltool.dropdown.service;


import top.lrshuai.excel.exceltool.entity.ChainDropDown;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 级联下拉数据集接口类
 * 每个下拉列表自己实现这个接口即可
 */
public interface IChainDropDownService {
    default ChainDropDown getSource(boolean isRoot,String... params) {
        Map<String,List<String>> dataMap = new HashMap<>();
        if(isRoot){
            // 第一层，没有父级
            List<String> root = getRoot(params);
            dataMap.put(ChainDropDown.ROOT_KEY,root);
        }else{
            // 有父级，key=父级，value=子集
            Map<String, List<String>> parentBindSubMap = getParentBindSubMap(params);
            dataMap.putAll(parentBindSubMap);
        }
        return new ChainDropDown().setRootFlag(isRoot).setDataMap(dataMap);
    }

    /**
     * 第一级的下拉
     */
    List<String> getRoot(String... params);

    /**
     * 二级及以上的下拉返回
     * 返回上一级与当前级别的map
     * 返回这个结构是为了添加 excel名称管理器
     */
    Map<String,List<String>> getParentBindSubMap(String... params);

}
