package com.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagemen// 加入事务管理
public class CommunityApplication {

    public static void main(String[] args) {
        //自动开启服务器，创建Spring容器
        SpringApplication.run(CommunityApplication.class, args);

    }

}
