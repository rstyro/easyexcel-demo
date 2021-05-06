package top.lrshuai.excel.exceltool.dropdown.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.Map;

/**
 * 导出 handler
 */
public class ExcelCellWriteHandler implements SheetWriteHandler {

    private final Map<Integer, String[]> map;

    /**
     * 设置阈值，避免生成的导入模板下拉值获取不到
     */
    private static final Integer LIMIT_NUMBER = 100;

    public ExcelCellWriteHandler(Map<Integer, String[]> map) {
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
            // 如果下拉值总数大于100，则使用一个新sheet存储，避免生成的导入模板下拉值获取不到
            if (v.length > LIMIT_NUMBER) {
                //定义sheet的名称
                //1.创建一个隐藏的sheet 名称为 hidden + k
                String sheetName = "hidden" + k;
                Workbook workbook = writeWorkbookHolder.getWorkbook();
                Sheet hiddenSheet = workbook.createSheet(sheetName);
                for (int i = 0, length = v.length; i < length; i++) {
                    // 开始的行数i，列数k
                    hiddenSheet.createRow(i).createCell(k).setCellValue(v[i]);
                }
                Name category1Name = workbook.createName();
                category1Name.setNameName(sheetName);
                String excelLine = getExcelLine(k);
                // =hidden!$H:$1:$H$50 sheet为hidden的 H1列开始H50行数据获取下拉数组
                String refers = "=" + sheetName + "!$" + excelLine + "$1:$" + excelLine + "$" + (v.length + 1);
                // 将刚才设置的sheet引用到你的下拉列表中
                DataValidationConstraint constraint = helper.createFormulaListConstraint(refers);
                DataValidation dataValidation = helper.createValidation(constraint, rangeList);
                writeSheetHolder.getSheet().addValidationData(dataValidation);
                // 设置存储下拉列值得sheet为隐藏
                int hiddenIndex = workbook.getSheetIndex(sheetName);
                if (!workbook.isSheetHidden(hiddenIndex)) {
                    workbook.setSheetHidden(hiddenIndex, true);
                }
            }
            // 下拉列表约束数据
            DataValidationConstraint constraint = helper.createExplicitListConstraint(v);
            // 设置约束
            DataValidation validation = helper.createValidation(constraint, rangeList);
            // 阻止输入非下拉选项的值
            validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
            validation.setShowErrorBox(true);
            validation.setSuppressDropDownArrow(true);
            validation.createErrorBox("提示", "此值与单元格定义格式不一致");
            // validation.createPromptBox("填写说明：","填写内容只能为下拉数据集中的单位，其他单位将会导致无法入仓");
            sheet.addValidationData(validation);
        });
    }

    /**
     * 返回excel列标A-Z-AA-ZZ
     *
     * @param num 列数
     * @return
     */
    private String getExcelLine(int num) {
        String line = "";
        int first = num / 26;
        int second = num % 26;
        if (first > 0) {
            line = (char) ('A' + first - 1) + "";
        }
        line += (char) ('A' + second) + "";
        return line;
    }
}
