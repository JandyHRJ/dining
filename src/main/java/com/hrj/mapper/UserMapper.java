package com.hrj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrj.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-28  16:46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
