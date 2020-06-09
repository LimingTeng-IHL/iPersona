package cn.trunch.auth.service;

import cn.trunch.auth.dao.UserDao;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class RegisterService {

    @Resource
    UserDao userDao;

    public Message checkUserExist(String userId) {
        User user = userDao.getUserInfo(userId);
        if (null != user) {
            return new Message(202, "注册失败", new User());
        }
    }

    public Message addUserInfo(HttpServletRequest request) {
        long id;
        String password;

        userDao.addUserInfo(id, password);
        return new Message(200, "注册成功", user);
    }
}