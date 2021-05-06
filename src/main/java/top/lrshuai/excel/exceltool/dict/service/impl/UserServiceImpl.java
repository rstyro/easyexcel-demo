package top.lrshuai.excel.exceltool.dict.service.impl;

import top.lrshuai.excel.exceltool.dict.entity.User;
import top.lrshuai.excel.exceltool.dict.mapper.UserMapper;
import top.lrshuai.excel.exceltool.dict.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rstyro
 * @since 2021-05-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
