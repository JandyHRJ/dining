package com.hrj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrj.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-07-01  16:46
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
