package top.lrshuai.excel.exceltool.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.excel.exceltool.entity.ExcelDataDto;
import top.lrshuai.excel.exceltool.listener.ExcelDataListener;

import java.io.IOException;

@Controller("/test")
public class WebTestController {

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ExcelDataDto.class, new ExcelDataListener()).sheet().doRead();
        return "success";
    }

}
