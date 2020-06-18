package cn.trunch.auth.service;

import cn.trunch.auth.dao.UserDao;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
//import com.alibaba.fastjson.JSONObject;
import cn.trunch.auth.util.HttpUtil;
import cn.trunch.auth.util.RpcHelper;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class RegisterService {

    @Resource
    UserDao userDao;

    public Message addUserInfo(HttpServletRequest request) {
        try {
            String result = RpcHelper.readJsonObject(request);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null) {
                String id = jsonObject.getString("user_id");
                String password = jsonObject.getString("user_password");
                userDao.addUserInfo(id, password);
                User user = userDao.getUserInfo(id);
                return new Message(200, "注册成功", user);
            } else {
                return new Message(400, "JSON为空", new User());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message(403, "注册失败", new User());
    }

    public Message checkUserExist(String userId) {
        User user = userDao.getUserInfo(userId);
        if (null != user) {
            return new Message(202, "check失败", new User());
        }
        return new Message(400, "check成功", user);
    }
}