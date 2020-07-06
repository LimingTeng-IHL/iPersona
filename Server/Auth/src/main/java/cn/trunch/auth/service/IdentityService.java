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
public class IdentityService {

    @Resource
    UserDao userDao;

    public Message checkUserIdentity(HttpServletRequest request) {
        try {
            String result = RpcHelper.readJsonObject(request);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null) {
                String name = jsonObject.getString("user_id");
                String id = name.toLowerCase();
                //传入的UserID已存在
                User user = userDao.getUserInfo(id);
                if (null != user) {
                    return new Message(202, "该用户已存在", new User());
                }
                return new Message(201, "用户名可用", new User());
            } else {
                return new Message(400, "JSON为空", new User());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Message(403, "注册失败", new User());
    }

}
