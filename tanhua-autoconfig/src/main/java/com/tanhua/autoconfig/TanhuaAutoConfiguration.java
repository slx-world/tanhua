package com.tanhua.autoconfig;

import com.tanhua.autoconfig.properties.SmsProperties;
import com.tanhua.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author: slx
 * @create: 2023/9/14
 */

@EnableConfigurationProperties({SmsProperties.class})
public class TanhuaAutoConfiguration {

    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties) {
        return new SmsTemplate(smsProperties);
    }

}
