package com.scu.szmt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.scu.szmt.mapper")
@SpringBootApplication
public class ScuCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScuCalendarApplication.class, args);
    }

}

