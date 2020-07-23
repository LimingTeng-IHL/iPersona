package cn.trunch.auth.service;

import cn.trunch.auth.dao.UserDao;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LoginService {

    @Resource
    UserDao userDao;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Message checkPassword(String userId, String userPassword) {
        User user = userDao.getUserInfo(userId);
        //password encryption match
        if (null == userPassword) {
            return new Message(201, "登录失败", new User());
        }
        if (null == user) {
            return new Message(202, "登录失败", new User());
        }
        boolean matched = bCryptPasswordEncoder.matches(userPassword, user.getUserPassword());

        if (!matched) {
            return new Message(203, "密码不正确", new User());
        }
        return new Message(200, "登录成功", user);
    }

    public Message getUserInfo(String userId) {
        User user = userDao.getUserInfo(userId);
        if (null == user)
            return new Message(201, "获取用户信息失败", new User());
        return new Message(200, "获取用户信息成功", user);
    }
}
