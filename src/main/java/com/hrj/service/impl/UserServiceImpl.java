package com.hrj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrj.entity.User;
import com.hrj.mapper.UserMapper;
import com.hrj.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-28  16:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
