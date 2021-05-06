package top.lrshuai.excel.exceltool.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseExcelDataListener<T> extends AnalysisEventListener<T> {

    private List<T> result = new ArrayList<>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        log.debug("解析到一条数据:{}",t);
        result.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }

    public List<T> getResult() {
        return result;
    }
}
