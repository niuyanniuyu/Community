package com.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
		//自动开启服务器，创建Spring容器
        SpringApplication.run(CommunityApplication.class, args);

    }

}
