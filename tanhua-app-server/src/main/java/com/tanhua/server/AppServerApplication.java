package com.tanhua.server;

import org.apache.catalina.core.ApplicationMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: slx
 * @create: 2023/9/14
 */

@SpringBootApplication
public class AppServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMapping.class, args);
    }

}
