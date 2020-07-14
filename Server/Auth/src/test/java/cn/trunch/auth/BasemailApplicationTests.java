package cn.trunch.auth;

import cn.trunch.auth.controller.MailController;
import cn.trunch.auth.entity.User;
import cn.trunch.auth.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasemailApplicationTests {

    @Value("${mail.receptionMail.addr}")
    private String receptionMailAddr;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailController mailController;

    /**
     * 测试 文本邮件
     * @throws Exception
     */
    @Test
    public void testSimpleMail() throws Exception {
        mailController.getCheckCode(receptionMailAddr);
    }



}

