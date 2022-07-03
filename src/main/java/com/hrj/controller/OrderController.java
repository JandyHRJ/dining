package com.hrj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrj.common.BaseContext;
import com.hrj.common.R;
import com.hrj.dto.DishDto;
import com.hrj.entity.Dish;
import com.hrj.entity.OrderDetail;
import com.hrj.entity.Orders;
import com.hrj.service.OrderDetailService;
import com.hrj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-07-01  16:55
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        orderService.submit(orders);
        return R.success("下单成功");
    }

    @GetMapping("/userPage")
    public R<Page> page(int page, int pageSize){
        Page<Orders> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        orderService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }


}
