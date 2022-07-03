package com.hrj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrj.entity.Orders;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-07-01  16:49
 */
public interface OrderService extends IService<Orders> {

    //用户下单
    public void submit(Orders orders);
}
