package com.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;


@Configuration
public class AlphaConfig {
    //自定义Bean
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
    }
}
