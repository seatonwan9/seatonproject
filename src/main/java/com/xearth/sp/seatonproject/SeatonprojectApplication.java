package com.xearth.sp.seatonproject;

import com.xearth.sp.seatonproject.base.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 项目启动类
 * @author wangxudong
 * @date 2020/3/07 19:19
 */
@SpringBootApplication
//@MapperScan("com.xearth.sp.seatonproject.dao")
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class SeatonprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatonprojectApplication.class, args);
    }

}
