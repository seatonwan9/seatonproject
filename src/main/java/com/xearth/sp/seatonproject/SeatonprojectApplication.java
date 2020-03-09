package com.xearth.sp.seatonproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.xearth.sp.seatonproject.dao")
public class SeatonprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatonprojectApplication.class, args);
    }

}
