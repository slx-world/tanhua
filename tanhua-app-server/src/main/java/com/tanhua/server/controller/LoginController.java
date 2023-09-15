package com.tanhua.server.controller;

import com.tanhua.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录——发送验证码
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map) {
        // 1、获取手机号码
        String mobile = (String) map.get("phone");

        // 2、调用service发送短信
        return userService.sendMsg(mobile);
    }

}
