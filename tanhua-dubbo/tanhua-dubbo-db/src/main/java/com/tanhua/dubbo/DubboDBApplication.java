package com.tanhua.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: slx
 * @create: 2023/9/15
 */

@SpringBootApplication
@MapperScan("com.tanhua.dubbo.mapper")
public class DubboDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDBApplication.class, args);
    }
}
