package com.itheima.test;

import com.tanhua.autoconfig.template.SmsTemplate;
import com.tanhua.server.AppServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class SmsTemplateTest {

    @Autowired
    private SmsTemplate smsTemplate;

    @Test
    public void testSendSms() {
        smsTemplate.sendSms("12345678910", "4567");

    }

}
