package top.lrshuai.excel.exceltool.dropdown.service;


import top.lrshuai.excel.exceltool.entity.ChainDropDown;

/**
 * 级联下拉数据集接口类
 * 每个下拉列表自己实现这个接口即可
 */
public interface IChainDropDownService {
    default public ChainDropDown getSource(String... params) {
        return new ChainDropDown();
    }


}
