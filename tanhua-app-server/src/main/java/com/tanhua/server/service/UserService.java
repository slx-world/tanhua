package com.tanhua.server.service;

import com.tanhua.autoconfig.template.SmsTemplate;
import com.tanhua.commons.utils.JwtUtils;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.model.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@Service
public class UserService {

    @DubboReference
    private UserApi userApi;

    @Autowired
    private SmsTemplate template;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证码校验登录
     * @param phone
     * @param code
     * @return
     */
    public Map loginVerification(String phone, String code) {
        // 1、从redis中获取下发的验证码
        String redisCode = redisTemplate.opsForValue().get("CHECK_CODE_" + phone);

        // 2、对验证码进行校验（验证码是否存在，是否和输入的验证码一致）
        if (StringUtils.isEmpty(redisCode) || !redisCode.equals(code)) {
            // 验证码无效
            throw new RuntimeException("验证码错误！");
        }

        // 3、删除redis中的验证码
        redisTemplate.delete("CHECK_CODE_" + phone);

        // 4、根据手机号码查询用户
        User user = userApi.findByMobile(phone);
        boolean isNew = false;

        // 5、如果用户不存在，创建用户保存到数据库中
        if (user == null) {
            user = new User();
            user.setMobile(phone);
            user.setPassword(DigestUtils.md5Hex("123456"));
            Long userId = userApi.save(user);
            user.setId(userId);
            isNew = true;
        }

        // 6、通过Jwt生成token（存入id和手机号码）
        Map tokenMap = new HashMap();
        tokenMap.put("id", user.getId());
        tokenMap.put("mobile", phone);
        String token = JwtUtils.getToken(tokenMap);

        // 7、构造返回值
        Map retMap = new HashMap();
        retMap.put("token", token);
        retMap.put("isNew", isNew);

        return retMap;
    }


    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    public void sendMsg(String phone) {
        // 1、生成验证码（6位数字）
        String code = RandomStringUtils.randomNumeric(6);
        // String code = "123456";

        // 2、调用template发送短信
        template.sendSms(phone, code);

        // 3、将验证码存入redis
        redisTemplate.opsForValue().set("CHECK_CODE_" + phone, code, Duration.ofMinutes(5)); // 验证码失效时间
    }

}
