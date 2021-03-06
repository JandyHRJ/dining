package com.hrj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hrj.common.R;
import com.hrj.entity.User;
import com.hrj.service.UserService;
import com.hrj.utils.EMailUtils;
import com.hrj.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-28  16:48
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EMailUtils eMailUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    //发送邮箱验证码
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取邮箱
        String eMail = user.getEmail();
        if (StringUtils.isNotEmpty(eMail)){
            //生成4位随机验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //调用邮箱发送工具类发送邮件
            eMailUtils.sendText(code,eMail);
            //保存验证码到session
            //session.setAttribute(eMail,code);
            //将验证码缓存到Redis中，设置有效期为5分钟
            redisTemplate.opsForValue().set(eMail, code ,5, TimeUnit.MINUTES);
            return R.success("验证码发送成功");
        }

        return R.error("邮件发送失败");
    }

    //移动端用户登录
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){
        //获取邮箱和验证码
        String email = map.get("email").toString();
        String code = map.get("code").toString();

        //从session获取保存的验证码
        //Object codeInSession = session.getAttribute(email);
        //从Redis中获取缓存的验证码
        Object codeInRedis = redisTemplate.opsForValue().get(email);
        //进行验证码的比对
        if (codeInRedis != null && codeInRedis.equals(code)){


            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getEmail,email);
            User user = userService.getOne(queryWrapper);
            //判断当前是否为新用户，如果新用户则自动注册
            if (user == null){
                user = new User();
                user.setEmail(email);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            //如果用户登录成功，删除Redis中缓存的验证码
            redisTemplate.delete(email);
            return R.success(user);
        }

        return R.error("登录失败");
    }

    //员工退出
    @PostMapping("/loginout")
    public R<String> logout(HttpServletRequest request) {
        //清理session中的员工id
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

}
