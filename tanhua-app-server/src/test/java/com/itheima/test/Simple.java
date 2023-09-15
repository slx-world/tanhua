package com.itheima.test;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;

/**
 * @author: slx
 * @create: 2023/9/14
 */

public class Simple {

    public static void main(String[] args) throws Exception {

        String accessKeyId = "";

        String accessKeySecret = "";

        // 配置阿里云
        Config config = new Config()
                // 你的AccessKey ID
                .setAccessKeyId(accessKeyId)

                // 你的Accesskey Secret
                .setAccessKeySecret(accessKeySecret);

        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers("")
                .setSignName("物流云商")
                .setTemplateCode("SMS_205134115")
                .setTemplateParam("{\"code\":\"1234\"}");

        SendSmsResponse response = client.sendSms(sendSmsRequest);
        SendSmsResponseBody body = response.getBody();
        System.out.println(body);
    }

}
