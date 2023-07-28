package com.example.mpproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mpproject.mapper")
public class MpProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpProjectApplication.class, args);

    }

}
