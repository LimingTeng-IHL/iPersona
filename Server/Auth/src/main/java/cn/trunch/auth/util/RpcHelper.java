package cn.trunch.auth.util;

//import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class RpcHelper {

    public static String readJsonObject(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "JSONObject ERROR";
    }

}
