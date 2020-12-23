package com.secondhand.xiaoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.secondhand"})
public class XiaoyiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoyiApplication.class, args);
    }

}
