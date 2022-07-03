package com.hrj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrj.dto.SetmealDto;
import com.hrj.entity.Setmeal;

import java.util.List;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  19:22
 */
public interface SetmealService extends IService<Setmeal> {
    //新增套餐。同时保存套餐和菜品的关联关系
    void saveWithDish(SetmealDto setmealDto);
    //删除套餐，同时删除关联数据
    void removeWithDish(List<Long> ids);
}
