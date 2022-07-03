package com.hrj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrj.common.CustomException;
import com.hrj.entity.Category;
import com.hrj.entity.Dish;
import com.hrj.entity.Setmeal;
import com.hrj.mapper.CategoryMapper;
import com.hrj.service.CategoryService;
import com.hrj.service.DishService;
import com.hrj.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  18:43
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    //根据id删除分类，删除之前判断关联
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = (int) dishService.count(dishLambdaQueryWrapper);
        //查询当前是否关联了菜品和套餐
        if (count > 0) throw new CustomException("当前分类项关联了菜品，不能删除");
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = (int) setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) throw new CustomException("当前分类项关联了套餐，不能删除");;
        //正常删除
        super.removeById(id);
    }
}
