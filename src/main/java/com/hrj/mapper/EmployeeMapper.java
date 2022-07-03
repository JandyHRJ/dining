package com.hrj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrj.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-10  20:01
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
