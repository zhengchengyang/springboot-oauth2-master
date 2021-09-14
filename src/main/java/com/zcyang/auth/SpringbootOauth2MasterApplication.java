package com.zcyang.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zcyang.auth.mapper")
public class SpringbootOauth2MasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootOauth2MasterApplication.class, args);
    }

}
