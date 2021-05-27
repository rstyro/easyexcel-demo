package top.lrshuai.excel.exceltool.dropdown.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import top.lrshuai.excel.exceltool.dict.entity.Area;
import top.lrshuai.excel.exceltool.dict.service.IAreaService;
import top.lrshuai.excel.exceltool.utils.ApplicationContextUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 区域级联下拉 实现类
 */
public class AreaChainDropDownService implements IChainDropDownService{

//    @Override
//    public List<ChainDropDown> getSource(String... params) {
//        if(ObjectUtils.isEmpty(params)){
//            return null;
//        }
//        IAreaService areaService = ApplicationContextUtils.getBean(IAreaService.class);
//        // 这里因为我知道params 是int
//        int level = Integer.parseInt(params[0]);
//        // 保存当前level的子集，key是当前level的ID
//        Map<Long,List<ChainDropDown>> childMap = new HashMap<>();
//        if(level<3){
//            // 这里是省市区，如果是小于3，那就是（省或者市）有子集的
//            List<Area> subAreaList = areaService.list(new LambdaQueryWrapper<Area>().eq(Area::getCtype, level+1));
//            if(!ObjectUtils.isEmpty(subAreaList)){
//                subAreaList.stream().forEach(sub->{
//                    if(childMap.containsKey(sub.getParentId())){
//                        childMap.get(sub.getParentId()).add(new ChainDropDown().setValue(sub.getCname()));
//                    }else {
//                        childMap.put(sub.getParentId(),Lists.newArrayList(new ChainDropDown().setValue(sub.getCname())));
//                    }
//                });
//            }
//        }
//        List<Area> areaList = areaService.list(new LambdaQueryWrapper<Area>().eq(Area::getCtype,level));
//        List<ChainDropDown> result =  new ArrayList();
//        if(!ObjectUtils.isEmpty(areaList)){
//            Boolean hasChild=childMap.size()>0;
//            areaList.stream().forEach(area -> {
//                ChainDropDown chainDropDown = new ChainDropDown().setValue(area.getCname()).setHasChild(hasChild);
//                if(hasChild){
//                    chainDropDown.setSubList(childMap.get(area.getId()));
//                }
//                result.add(chainDropDown);
//            });
//        }
//        return result;
//    }


//    @Override
//    public ChainDropDown getSource(boolean isRoot,String... params) {
//        Map<String,List<String>> dataMap = new HashMap<>();
//        ChainDropDown chainDropDown = new ChainDropDown().setRootFlag(isRoot);
//        if(isRoot){
//            // 第一层，没有父级
//            List<String> root = getRoot(params);
//            dataMap.put(ChainDropDown.ROOT_KEY,root);
//        }else{
//            // 有父级，key=父级，value=子集
//            Map<String, List<String>> parentBindSubMap = getParentBindSubMap(params);
//            dataMap.putAll(parentBindSubMap);
//        }
//        return chainDropDown.setRootFlag(isRoot).setDataMap(dataMap);
//    }

    /**
     * 第一层，key=root,value=可选数组
     */
    @Override
    public List<String> getRoot(String... params){
        IAreaService areaService = ApplicationContextUtils.getBean(IAreaService.class);
        // 这里cType=1 得到第一次下拉，根据业务需要
        List<Area> areaList = areaService.list(new LambdaQueryWrapper<Area>().eq(Area::getCtype,1));
        return areaList.stream().map(Area::getCname).collect(Collectors.toList());
    }

    /**
     * 获取子类的Map
     */
    @Override
    public Map<String,List<String>> getParentBindSubMap(String... params){
        // 级别
        int level = Integer.parseInt(params[0]);
        IAreaService areaService = ApplicationContextUtils.getBean(IAreaService.class);
        // 当前级别的所有区域
        List<Area> areaList = areaService.list(new LambdaQueryWrapper<Area>().eq(Area::getCtype,level));
        // 因为走这个，说明它是有父级的，
        // 当前级别的所有父级
        Set<Long> parentIds = new HashSet<>();
        // 父级下面的子集
        Map<Long,List<String>> childMap = new HashMap<>();
        areaList.forEach(i->{
            parentIds.add(i.getParentId());
            if(childMap.containsKey(i.getParentId())){
                childMap.get(i.getParentId()).add(i.getCname());
            }else{
                List<String> childList = new ArrayList<>();
                childList.add(i.getCname());
                childMap.put(i.getParentId(),childList);
            }
        });
        // key 是父级，value 是父级的子类
        Map<String,List<String>> dataMap = new HashMap<>();
        List<Area> parentAreaList = areaService.list(new LambdaQueryWrapper<Area>().in(Area::getId, parentIds));
        parentAreaList.stream().forEach(parent->{
            dataMap.put(parent.getCname(),childMap.get(parent.getId()));
        });
        return dataMap;
    }
}
