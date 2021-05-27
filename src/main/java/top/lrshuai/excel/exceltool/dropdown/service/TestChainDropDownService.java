package top.lrshuai.excel.exceltool.dropdown.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域级联下拉 实现类
 */
public class TestChainDropDownService implements IChainDropDownService{

    /**
     * 第一层，key=root,value=可选数组
     */
    @Override
    public List<String> getRoot(String... params){
        return Arrays.asList(new String[]{"中国", "美国"});
    }

    /**
     * 获取子类的Map
     */
    @Override
    public Map<String,List<String>> getParentBindSubMap(String... params){
        int level = Integer.parseInt(params[0]);
        // key 是父级，value 是父级的子类
        Map<String,List<String>> dataMap = new HashMap<>();
        if(level==2){
            dataMap.put("中国",Arrays.asList(new String[]{"北京2", "广东2"}));
            dataMap.put("美国",Arrays.asList(new String[]{"阿拉斯加州", "阿拉巴马州"}));
        }else if(level == 3){
            dataMap.put("北京2",Arrays.asList(new String[]{"北京市2"}));
            dataMap.put("广东2",Arrays.asList(new String[]{"广州2","深圳2"}));
            dataMap.put("阿拉斯加州",Arrays.asList(new String[]{"阿拉斯加","雅库塔特"}));
            dataMap.put("阿拉巴马州",Arrays.asList(new String[]{"马伦戈县"}));
        }else if(level == 4){
            dataMap.put("北京市2",Arrays.asList(new String[]{"朝阳区2","密云区2"}));
            dataMap.put("广州2",Arrays.asList(new String[]{"天河区2","白云区2"}));
            dataMap.put("深圳2",Arrays.asList(new String[]{"福田区2","南山区2"}));
            dataMap.put("阿拉斯加",Arrays.asList(new String[]{"瞎编区","编不下去了"}));
            dataMap.put("雅库塔特",Arrays.asList(new String[]{"瞎编区","编不下去了"}));
            dataMap.put("马伦戈县",Arrays.asList(new String[]{"马勒戈壁"}));
        }
        return dataMap;
    }
}
