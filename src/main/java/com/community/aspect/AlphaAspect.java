package com.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
*
    around before
    before
    2021-05-08 21:37:14,659 DEBUG [http-nio-8080-exec-1] c.c.d.D.selectDiscussPosts [BaseJdbcLogger.java:137] ==>  Preparing: select id,user_id,title, content,type,status,create_time,comment_count,score from discuss_post where status !=2 order by type desc, create_time desc limit ?,?
    2021-05-08 21:37:14,659 DEBUG [http-nio-8080-exec-1] c.c.d.D.selectDiscussPosts [BaseJdbcLogger.java:137] ==> Parameters: 0(Integer), 10(Integer)
    2021-05-08 21:37:14,666 DEBUG [http-nio-8080-exec-1] c.c.d.D.selectDiscussPosts [BaseJdbcLogger.java:137] <==      Total: 10
    afterReturning
    after
    around after
* */

// 测试
//@Component
//@Aspect
public class AlphaAspect {
    // *[返回值] com.community.service.*[类名].*[方法名](..[所有参数]))
    @Pointcut("execution(* com.community.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    // 返回值以后
    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    // 出现异常后
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}
