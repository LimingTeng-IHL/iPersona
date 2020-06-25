package cn.trunch.auth.controller;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.service.LoginService;

import cn.trunch.auth.service.RegisterService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Message addUser(HttpServletRequest request)  {
        return registerService.addUserInfo(request);
    }
}