package com.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义拦截器
 */
// 作用于METHOD|PACKAGE|或者其他的范围
@Target(ElementType.METHOD)
// 声明这个注解生效的时机
@Retention(RetentionPolicy.RUNTIME)
// 自定义注解，需要登录后访问
public @interface LoginRequired {

}
