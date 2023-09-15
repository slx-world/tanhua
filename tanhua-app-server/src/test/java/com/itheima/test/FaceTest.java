package com.itheima.test;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: slx
 * @create: 2023/9/15
 */

public class FaceTest {

    public static final String APP_ID = "";

    public static final String API_KEY = "";

    public static final String SECRET_KEY = "";

    public static void main(String[] args) {
        // 初始化AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String image = "";
        String imageType = "URL";
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
        JSONObject res = client.detect(image, imageType, options);
        System.out.println(res.toString(2));
    }

}
