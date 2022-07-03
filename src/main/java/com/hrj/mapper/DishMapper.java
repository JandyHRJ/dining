package com.hrj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrj.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  19:20
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
