package top.lrshuai.excel.exceltool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 级联下拉框
 */
@AllArgsConstructor
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ChainDropDown {
//    /**
//     * 名称
//     */
//    private String value;
//    /**
//     * 是否有子类
//     */
//    private boolean hasChild=true;
//    /**
//     * 子类的集合
//     */
//    private List<ChainDropDown> subList;

    /**
     * 是否是根目录
     */
    private boolean rootFlag=true;

//    private List<String> data;

    /**
     * 行下标
     */
    private Integer rowIndex=0;

    private Map<String,List<String>> dataMap=new HashMap<>();


}



