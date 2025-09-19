package top.lrshuai.excel.exceltool.dropdown.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.ObjectUtils;
import top.lrshuai.excel.exceltool.dict.entity.DictValue;
import top.lrshuai.excel.exceltool.dict.service.IDictValueService;
import top.lrshuai.excel.exceltool.utils.ApplicationContextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 职业的下拉 实现类
 */
public class OccupationDropDownService implements IDropDownService{

    @Override
    public String[] getSource(String typeValue) {
        // 没法用 @Autowired 注入，所以用ApplicationContext
        IDictValueService dictValueService = ApplicationContextUtils.getBean(IDictValueService.class);
        List<DictValue> list = dictValueService.list(new LambdaQueryWrapper<DictValue>().eq(DictValue::getType, typeValue));
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        Set<String> collect = list.stream().map(DictValue::getValue).collect(Collectors.toSet());
        return collect.toArray(new String[collect.size()]);
    }

    @Override
    public String[] getSource(String... params) {
        IDictValueService dictValueService = ApplicationContextUtils.getBean(IDictValueService.class);
        QueryWrapper<DictValue> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(params)){
            Map<String, String> paramMap = new HashMap<>();
            for (String param : params) {
                String[] split = param.split("=", 2);
                if (split.length == 2) {
                    paramMap.put(split[0], split[1]);
                }
            }
            paramMap.forEach(queryWrapper::eq);
        }
        List<DictValue> list = dictValueService.list(queryWrapper);
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        return list.stream().map(DictValue::getValue).toArray(String[]::new);
    }
}
