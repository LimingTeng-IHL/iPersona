package cn.trunch.auth.service;

import cn.trunch.auth.dao.UserDao;
import cn.trunch.auth.entity.Message;
import cn.trunch.auth.entity.User;
//import com.alibaba.fastjson.JSONObject;
import cn.trunch.auth.util.HttpUtil;
import cn.trunch.auth.util.RpcHelper;

import org.json.JSONException;
import org.json.JSONObject;
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

    public Message checkUserExist(String userId) {
        User user = userDao.getUserInfo(userId);
        if (null != user) {
            return new Message(202, "注册失败", new User());
        }
        return new Message(200, "注册成功", user);
    }

//    public Message addUserInfo(HttpServletRequest request) {
//
//        String fakeip = "49.74.160.84";
//        String url = "http://api.map.baidu.com/location/ip?ip=" + fakeip + "&ak=nSxiPohfziUaCuONe4ViUP2N&coor=bd09ll";
//        String param = "";
//        String result = HttpUtil.doPost(url, param);
//        JSONObject input = JSONObject.parseObject(result);
//        String id = input.getString("user_id");
//        String password = input.getString("user_password");
//        userDao.addUserInfo(id, password);
//        User user = userDao.getUserInfo(id);
//        return new Message(200, "注册成功", user);
//    }

    public Message addUserInfo(HttpServletRequest request) {
        try {
            JSONObject input = RpcHelper.readJsonObject(request);
            String id = input.getString("user_id");
            String password = input.getString("user_password");

            userDao.addUserInfo(id, password);

            User user = userDao.getUserInfo(id);
            return new Message(200, "注册成功", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message(202, "注册失败", new User());
    }
}