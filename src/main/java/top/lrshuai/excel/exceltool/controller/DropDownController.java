package top.lrshuai.excel.exceltool.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lrshuai.excel.exceltool.dict.entity.User;
import top.lrshuai.excel.exceltool.dict.service.IUserService;
import top.lrshuai.excel.exceltool.dropdown.template.UserTemplate;
import top.lrshuai.excel.exceltool.entity.UserDto;
import top.lrshuai.excel.exceltool.utils.EasyExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * excel 下拉相关
 */
@Controller
@RequestMapping("/dropDown")
public class DropDownController {

    @Autowired
    private IUserService userService;

    /**
     * 下载下拉模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        List<UserTemplate> export = new ArrayList<>(Arrays.asList(new UserTemplate()));
        EasyExcelUtils.exportBrowser(export, UserTemplate.class, export.size(), "模板", response);
    }

    /**
     * 导出用户
     * @param response
     * @throws Exception
     */
    @GetMapping("/exportUser")
    public void export(HttpServletResponse response) throws Exception {
        List<User> list = userService.list();
        String jsonUser = JSON.toJSONString(list);
        List<UserDto> userDtoList = JSON.parseArray(jsonUser, UserDto.class);
        EasyExcelUtils.exportBrowser(userDtoList, UserDto.class, userDtoList.size(), "用户信息", response);
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
