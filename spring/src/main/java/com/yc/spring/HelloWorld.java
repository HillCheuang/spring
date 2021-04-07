package com.yc.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-04 14:41
 */
@Component
public class HelloWorld {

    HelloWorld(){
        System.out.println("构造方法");
    }


    public void hello(){
        System.out.println("helloworld");
    }


}