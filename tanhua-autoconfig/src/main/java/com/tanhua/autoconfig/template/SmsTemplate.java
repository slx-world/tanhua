package com.tanhua.autoconfig.template;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.tanhua.autoconfig.properties.SmsProperties;

/**
 * @author: slx
 * @create: 2023/9/14
 */

public class SmsTemplate {

    private SmsProperties properties;

    public SmsTemplate(SmsProperties properties) {
        this.properties = properties;
    }

    public void sendSms(String mobile, String code) {
        Config config = new Config()
                .setAccessKeyId(properties.getAccessKey())
                .setAccessKeySecret(properties.getSecret())
                .setEndpoint("dysmsapi.aliyuncs.com");

        try {
            Client client = new Client(config);

            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)
                    .setSignName(properties.getSignName())
                    .setTemplateCode(properties.getTemplateCode())
                    .setTemplateParam("{\"code\":\"" + code + "\"}");

            SendSmsResponse response = client.sendSms(sendSmsRequest);
            System.out.println(response.getBody().toString());
            System.out.println(response.getBody().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
