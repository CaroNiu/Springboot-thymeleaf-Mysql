package com.spring.boot.blog.service;

import com.spring.boot.blog.domain.User;
import com.spring.boot.blog.vo.EmailSendUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;

/**
 * 邮件发送服务
 * @author Nuri
 * @CreateTime 2021/2/26
 * @Describe
 */
@Service
@Slf4j
public class EmailService {

    // 注入邮件发送服务
    @Autowired
    private JavaMailSender javaMailSender;

    // 获取发送地址
    @Value("${spring.mail.username}")
    private String sendEmailAddress;

    /**
     * 发送邮件
     * @param userEvent
     * @return
     */
    @Async
    @EventListener
    public String commonEmail(EmailSendUserEvent userEvent) {
        // 获取注册信息
        User sendUser = (User)userEvent.getSource();
        log.info("监听获取邮件发送...");
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(sendEmailAddress);
        //谁要接收
        message.setTo(sendUser.getEmail());
        //邮件标题
        message.setSubject("Congratulations on " + sendUser.getName()+ " successful registration\n" +
                "\n");
        //邮件内容
        message.setText("\n" +
                "Your account number is："+sendUser.getName()+",Please keep your account properly\n" +
                "\n");
        try {
            javaMailSender.send(message);
            log.info("发送邮件成功");
            return "邮件发送成功";
        } catch (MailException e) {
            e.printStackTrace();
            log.info("邮件发送失败，异常："+e.getLocalizedMessage());
            return "邮件发送失败";
        }
    }
}
