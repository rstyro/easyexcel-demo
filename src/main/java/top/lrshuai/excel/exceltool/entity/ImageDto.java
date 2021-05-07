package top.lrshuai.excel.exceltool.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

@ColumnWidth(25)
@ContentRowHeight(100)
@Data
public class ImageDto {

    private File file;

    private InputStream inputStream;

    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;

    private byte[] byteArray;

    /**
     * 根据url导出
     * @since 2.1.1
     */
    private URL url;
}
