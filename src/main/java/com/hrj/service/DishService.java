package com.hrj.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrj.dto.DishDto;
import com.hrj.entity.Dish;

import java.util.List;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  19:21
 */
public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要同时操作两张表: dish、dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和口味信息
    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息
    void updateWithFlavor(DishDto dishDto);
}
