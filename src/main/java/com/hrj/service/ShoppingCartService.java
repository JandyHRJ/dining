package com.hrj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrj.entity.ShoppingCart;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-07-01  11:48
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    ShoppingCart getData(ShoppingCart shoppingCart);
}
