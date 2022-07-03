package com.hrj.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送邮件类
 * @Author: 黄仁杰
 * @CreateTime: 2022-06-28  15:27
 */
@Component
public class EMailUtils {
    private static final Logger logger = LoggerFactory.getLogger(EMailUtils.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;



    public void sendText(String txt, String to) {
        String from = environment.getProperty("spring.mail.from");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject("验证码");
        msg.setText(txt);

        this.mailSender.send(msg);
        logger.info("send text done");
    }


}
