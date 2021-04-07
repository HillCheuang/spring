package com.yc.spring;

import com.yc.AppConfig;
import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-04 14:49
 */
@ComponentScan(basePackages = "com.yc.spring")
public class Test extends TestCase {

    private ApplicationContext ac; //spring容器

    @Override
    @Before
    public void setUp(){

        ac =new AnnotationConfigApplicationContext(AppConfig.class);
        //读取 AppConfig.class ->basePackages ="com.yc" ->得到要扫描的类
        //检查这些包是否有@Compent注释，如有，则实例化
        //存到一个Map<String,Object>  ====ac

    }

    @org.junit.Test
    public void testHello(){
        System.out.println( Integer.MAX_VALUE);
        HelloWorld helloWolrd = (HelloWorld)ac.getBean("helloWorld");
        helloWolrd.hello();

         helloWolrd = (HelloWorld)ac.getBean("helloWorld");
        helloWolrd.hello();
    }
}
