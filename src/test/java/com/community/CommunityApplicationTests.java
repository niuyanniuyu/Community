package com.community;

import com.community.dao.AlphaDao;
import com.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;


import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
//以CommunityApplication为配置类运行
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext() {
        System.out.println(applicationContext);
        //通常用类名获取Bean
        AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());

        //使用自定义名称获取Bean
        alphaDao = (AlphaDao) applicationContext.getBean("alphaHibernate");
        //alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
        System.out.println(alphaDao.select());
    }

    @Test
    public void testBeanManagement() {
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
        //Spring默认单例模式，更改@Scope("prototype")变为多例
        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig() {
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    //依赖注入方法
    @Autowired
    private AlphaDao alphaDao1;

    @Autowired
    @Qualifier("alphaHibernate")
    private AlphaDao alphaDao2;

    @Test
    public void TestDI() {
        System.out.println(alphaDao1);
        System.out.println(alphaDao2);
    }


}
