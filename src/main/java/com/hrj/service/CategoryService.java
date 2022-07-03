package com.hrj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrj.entity.Category;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  18:43
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
