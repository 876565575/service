package com.xc.cmsclient;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CmsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsClientApplication.class, args);
    }

}
