package com.hrj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrj.common.CustomException;
import com.hrj.dto.SetmealDto;
import com.hrj.entity.Setmeal;
import com.hrj.entity.SetmealDish;
import com.hrj.mapper.SetmealMapper;
import com.hrj.service.SetmealDishService;
import com.hrj.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  19:23
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    //新增套餐。同时保存套餐和菜品的关联关系
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息,操作setmeal
        //this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish
        setmealDishService.saveBatch(setmealDishes);

    }

    //删除套餐，同时删除关联数据
    @Transactional
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = (int) this.count(queryWrapper);
        //如果不能删除，抛出异常
        if (count > 0) throw new CustomException("套餐正在售卖中，不能删除");
        this.removeByIds(ids);
        //删除关联表的数据
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
