package top.lrshuai.excel.exceltool.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.excel.exceltool.entity.ExcelDataDto;
import top.lrshuai.excel.exceltool.listener.ExcelDataListener;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
@RequestMapping("/test")
public class WebTestController {


    @GetMapping("/ping")
    @ResponseBody
    public String ping(){
        return "pong";
    }

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ExcelDataDto.class, new ExcelDataListener()).sheet().doRead();
        return "success";
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDataDto.class).sheet("模板").doWrite(new ArrayList());
    }
}
