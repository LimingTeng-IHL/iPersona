package cn.trunch.auth.controller;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Message getCheckCode(String userEmail){
        //log.info("进入方法getCheckCode:"+user.toString());
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String emailMessage = "Your Token is : " + checkCode;
        return mailService.sendSimpleMail(userEmail, "Email Verification Token", emailMessage, checkCode);
    }

}

