package top.lrshuai.excel.exceltool.dropdown.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import top.lrshuai.excel.exceltool.entity.ChainDropDown;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * excel 级联下拉框 handler
 */
public class ChainDropDownWriteHandler implements SheetWriteHandler {

    private final Map<Integer, List<ChainDropDown>> map;

    /**
     * 设置阈值，避免生成的导入模板下拉值获取不到
     */
    private static final Integer LIMIT_NUMBER = 100;

    public ChainDropDownWriteHandler(Map<Integer, List<ChainDropDown>> map) {
        this.map = map;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // 这里可以对cell进行任何操作
        Sheet sheet = writeSheetHolder.getSheet();
        DataValidationHelper helper = sheet.getDataValidationHelper();

        // k 为存在下拉数据集的单元格下表 v为下拉数据集
        map.forEach((k, v) -> {
            // 设置下拉单元格的首行 末行 首列 末列
            CellRangeAddressList rangeList = new CellRangeAddressList(1, 65536, k, k);

            Set<String> collect = v.stream().map(ChainDropDown::getValue).collect(Collectors.toSet());
            String[] values = collect.toArray(new String[collect.size()]);
            // v 就是下拉列表的具体数据，下拉列表约束数据
            DataValidationConstraint constraint = helper.createExplicitListConstraint(values);
            // 设置下拉约束
            DataValidation validation = helper.createValidation(constraint, rangeList);
            // 阻止输入非下拉选项的值
            validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            validation.setShowErrorBox(true);
            validation.setSuppressDropDownArrow(true);
            validation.createErrorBox("提示", "此值与单元格定义格式不一致");
            sheet.addValidationData(validation);
        });

    }

    /**
     * 获取Excel列的号码A-Z - AA-ZZ - AAA-ZZZ 。。。。
     * @param num
     * @return
     */
    private static String getColNum(int num) {
        int MAX_NUM = 26;
        char initChar = 'A';
        if(num == 0){
            return initChar+"";
        }else if(num > 0 && num < MAX_NUM){
            int result = num % MAX_NUM;
            return (char) (initChar + result) + "";
        }else if(num >= MAX_NUM){
            int result = num / MAX_NUM;
            int mod = num % MAX_NUM;
            String starNum = getColNum(result-1);
            String endNum = getColNum(mod);
            return starNum+endNum;
        }
        return "";
    }

}
