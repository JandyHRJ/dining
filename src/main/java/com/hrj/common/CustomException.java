package com.hrj.common;

/**
 * @Description: 自定义业务异常
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-12  19:33
 */
public class CustomException  extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
