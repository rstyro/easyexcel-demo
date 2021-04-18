package top.lrshuai.excel.exceltool.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import top.lrshuai.excel.exceltool.entity.ExcelDataDto;

@Slf4j
public class ExcelDataListener extends AnalysisEventListener<ExcelDataDto> {

    public ExcelDataListener(){}

//    private Service todoService;
//    public ExcelDataListener(Service todoService){
//        this.todoService = todoService;
//    }

    @Override
    public void invoke(ExcelDataDto excelDataDto, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(excelDataDto));
        // todo 保存数据库
        // todoService.save();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
