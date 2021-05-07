package top.lrshuai.excel.exceltool.dropdown.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.ObjectUtils;
import top.lrshuai.excel.exceltool.dict.entity.DictValue;
import top.lrshuai.excel.exceltool.dict.service.IDictValueService;
import top.lrshuai.excel.exceltool.utils.ApplicationContextUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 区域级联下拉 实现类
 */
public class AreaChainDropDownService implements IDropDownService{

    @Override
    public String[] getSource(String... params) {
        // 没法用 @Autowired 注入，所以用ApplicationContext
        IDictValueService dictValueService = ApplicationContextUtils.getBean(IDictValueService.class);
        List<DictValue> list = dictValueService.list(new LambdaQueryWrapper<DictValue>().eq(DictValue::getType, params));
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        Set<String> collect = list.stream().map(DictValue::getValue).collect(Collectors.toSet());
        return collect.toArray(new String[collect.size()]);
    }
}
