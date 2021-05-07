package top.lrshuai.excel.exceltool.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.excel.exceltool.dropdown.annotation.DropDownFields;
import top.lrshuai.excel.exceltool.dropdown.handler.DropDownWriteHandler;
import top.lrshuai.excel.exceltool.dropdown.handler.ResolveAnnotation;
import top.lrshuai.excel.exceltool.listener.BaseExcelDataListener;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
public class EasyExcelUtils {

    /**
     * 导出excel文件
     * 官网文档地址：https://www.yuque.com/easyexcel/doc/easyexcel
     * @param data 数据
     * @param templateClass 模板对象class
     * @param pageSize 每页多少条
     * @param fileName 文件名称
     * @param response 输出流
     * @throws Exception err
     */
    public static void export(List data, Class templateClass, Integer pageSize, String fileName, HttpServletResponse response) throws Exception {
        pageSize = Optional.ofNullable(pageSize).orElse(50000);
        fileName = fileName + System.currentTimeMillis();
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 获取改类声明的所有字段
        Field[] fields = templateClass.getDeclaredFields();
        // 响应字段对应的下拉集合
        Map<Integer, String[]> map = new HashMap<>();
        Field field = null;
        // 循环判断哪些字段有下拉数据集，并获取
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            // 解析注解信息
            DropDownFields dropDownField = field.getAnnotation(DropDownFields.class);
            if (null != dropDownField) {
                String[] sources = ResolveAnnotation.resolve(dropDownField);
                if (null != sources && sources.length > 0) {
                    map.put(i, sources);
                }
            }
        }

        ExcelWriter excelWriter = null;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            excelWriter = EasyExcel.write(out, templateClass).registerWriteHandler(new DropDownWriteHandler(map)).build();
            // 分页
            List<List> lt = Lists.partition(data, pageSize);
            for (int i = 0; i < lt.size(); i++) {
                int j = i + 1;
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "第" + j + "页").build();
                excelWriter.write(lt.get(i), writeSheet);
            }
        } catch (Exception e) {
            response.setHeader("Content-Disposition", "attachment;filename=下载失败");
            e.printStackTrace();
            log.error("文档下载失败:" + e.getMessage());
        } finally {
            data.clear();
            if (excelWriter != null) {
                excelWriter.finish();
            }
            assert out != null;
            out.flush();
            out.close();
        }
    }


    /**
     * 读取excel 并解析
     * @param file 文件
     * @param clazz 解析成哪个dto
     * @param <T> t
     * @return list
     * @throws IOException error
     */
    public static <T> List<T>  read(MultipartFile file,Class<T> clazz) throws IOException {
        BaseExcelDataListener<T> baseExcelDataListener = new BaseExcelDataListener();
        EasyExcel.read(file.getInputStream(), clazz, baseExcelDataListener).sheet().doRead();
        return baseExcelDataListener.getResult();
    }

}
