package com.xearth.sp.seatonproject;

import com.xearth.sp.seatonproject.dao.impl.BaseDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 项目启动类
 * @author wangxudong
 * @date 2020/3/07 19:19
 */
//@MapperScan("com.xearth.sp.seatonproject.dao")
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
@EnableWebSocket
@EnableScheduling
@SpringBootApplication
public class SeatonprojectApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(SeatonprojectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SeatonprojectApplication.class, args);
    }

}
