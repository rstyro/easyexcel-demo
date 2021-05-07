package top.lrshuai.excel.exceltool.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.excel.exceltool.dict.entity.User;
import top.lrshuai.excel.exceltool.dict.service.IUserService;
import top.lrshuai.excel.exceltool.entity.ImageDto;
import top.lrshuai.excel.exceltool.entity.UserDto;
import top.lrshuai.excel.exceltool.utils.EasyExcelUtils;
import top.lrshuai.excel.exceltool.utils.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考图片导出：https://www.yuque.com/easyexcel/doc/write#cb1b271f
 */
@Controller
@RequestMapping("/images")
public class ImagesController {

    @Autowired
    private IUserService userService;

    /**
     * 导出图片类型
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("images/avatar.jpg");
        InputStream inputStream =classPathResource.getInputStream();
//        InputStream resourceAsStream = this.getClass().getResourceAsStream("/images/avatar.jpg");

        String fileName = URLEncoder.encode("图片excel", "UTF-8").replaceAll("\\+", "%20");
        try {
            List<ImageDto> list = new ArrayList<ImageDto>();
            ImageDto imageDto = new ImageDto();
            list.add(imageDto);
            File file = ResourceUtils.getFile("classpath:images/avatar.jpg");
            // 放入五种类型的图片 实际使用只要选一种即可
            imageDto.setByteArray(FileUtils.readFileToByteArray(file));
            imageDto.setFile(file);
            imageDto.setString(file.getAbsolutePath());
            imageDto.setInputStream(inputStream);
            imageDto.setUrl(new URL("http://portrait.gitee.com/uploads/avatars/user/286/858520_rstyro_1578934054.png!avatar30"));

            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ImageDto.class).sheet().doWrite(list);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 导入用户
     * @throws Exception
     */
    @GetMapping("/upload")
    @ResponseBody
    public String update(MultipartFile file) throws Exception {
        List<UserDto> result = EasyExcelUtils.read(file,UserDto.class);
        List<User> toDbUserList = JSON.parseArray(JSON.toJSONString(result), User.class);
        userService.saveBatch(toDbUserList,toDbUserList.size());
        return "ok";
    }
}
