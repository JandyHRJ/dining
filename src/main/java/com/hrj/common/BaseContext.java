package com.hrj.common;

/**
 * @Description: 基于ThreadLocal类，用于保存和获取当前登录用户id
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-11  23:26
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
