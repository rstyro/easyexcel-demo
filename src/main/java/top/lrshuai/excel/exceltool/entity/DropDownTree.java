package top.lrshuai.excel.exceltool.entity;

import java.util.List;

public class DropDownTree {
    /**
     * 是不是 根
     */
    private boolean rootFlag;

    /**
     * 节点的名称
     */
    private String name;

    /**
     * 子节点
     */
    private List<DropDownTree> subTree;
}
