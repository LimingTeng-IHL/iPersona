package cn.trunch.auth.service;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    public Message sendSimpleMail(String to, String title, String content, String checkCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        try {
            javaMailSender.send(mailMessage);
            log.info("邮件发送成功");
            return new Message(200, "邮件发送成功", checkCode);
        } catch (Exception e) {
            log.error("发送邮件时发生异常！", e);
            return new Message(400, "邮件发送失败", "");
        }
    }
}

