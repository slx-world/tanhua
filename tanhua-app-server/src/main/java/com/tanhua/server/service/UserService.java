package com.tanhua.server.service;

import com.easemob.im.server.api.user.UserApi;
import com.tanhua.autoconfig.template.SmsTemplate;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@Service
public class UserService {

    @Reference
    private UserApi userApi;

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 对手机号码发送验证码
    public ResponseEntity sendMsg(String mobile) {
        // 1、生成验证码（6位数字）
        String code = RandomStringUtils.randomNumeric(6);

        // 2、调用template发送短信
        smsTemplate.sendSms(mobile, code);

        // 3、存入redis
        redisTemplate.opsForValue().set("CHECK_CODE_" + mobile, code, Duration.ofMinutes(5)); // 验证码失效时间

        return ResponseEntity.ok(null);
    }
}
