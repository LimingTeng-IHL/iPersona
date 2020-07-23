package cn.trunch.auth.service;

import cn.trunch.auth.dao.UserDao;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
//import com.alibaba.fastjson.JSONObject;
import cn.trunch.auth.util.HttpUtil;
import cn.trunch.auth.util.RpcHelper;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
@Transactional
public class RegisterService {

    @Resource
    UserDao userDao;

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Message addUserInfo(HttpServletRequest request) {
        try {
            String result = RpcHelper.readJsonObject(request);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null) {
                String name = jsonObject.getString("user_id");
                String id = name.toLowerCase();
                String password = jsonObject.getString("user_password");
                String email = jsonObject.getString("user_email");
                //传入的UserID已存在
                User user = userDao.getUserInfo(id);
                if (null != user) {
                    return new Message(202, "该用户已存在", new User());
                }

                //password encryption
                String encryptPassword = bCryptPasswordEncoder.encode(password);

                //传入的UserID不存在
                userDao.addUserInfo(id, encryptPassword, name, email);
                User newUser = userDao.getUserInfo(id);
                return new Message(200, "注册成功", newUser);
            } else {
                return new Message(400, "JSON为空", new User());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message(403, "注册失败", new User());
    }

}