package com.hrj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrj.common.BaseContext;
import com.hrj.entity.ShoppingCart;
import com.hrj.mapper.ShoppingCartMapper;
import com.hrj.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-07-01  11:48
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Override
    public ShoppingCart getData(ShoppingCart shoppingCart) {
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);
        if (dishId != null) {
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        } else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }
        ShoppingCart cartServiceOne = this.getOne(queryWrapper);
        return cartServiceOne;
    }
}
