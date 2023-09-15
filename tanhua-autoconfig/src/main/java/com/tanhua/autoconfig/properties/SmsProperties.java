package com.tanhua.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: slx
 * @create: 2023/9/14
 */

@Data
@ConfigurationProperties(prefix = "tanhua.sms")
public class SmsProperties {

    private String signName;

    private String templateCode;

    private String accessKey;

    private String secret;

}
