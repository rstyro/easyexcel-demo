package top.lrshuai.excel.exceltool.dropdown.service;


/**
 * 动态下拉数据集接口类
 * 每个下拉列表自己实现这个接口即可
 */
public interface IDropDownService {
    public String[] getSource(String typeValue);
}
