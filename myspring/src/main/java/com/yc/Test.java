package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.bean.test.Hello;
import com.yc.config.AppConfig;
import com.yc.config.TestP;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:45
 */
public class Test {



        public static void main(String[] args) {
        MyApplicationContext ac =new MyAnnotationConfigApplicationContext(AppConfig.class);

//        MyApplicationContext ac =new MyAnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld helloWorld = (HelloWorld)ac.getBean("helloWorld");
        helloWorld.say();
//        TestP test = (TestP)ac.getBean("testP");
//        Random rd = (Random) ac.getBean("rd");
//        System.err.println(rd);
//        test.sayOne();
            Hello hello = (Hello)ac.getBean("hello");
            hello.say();
            helloWorld.hello.say();
    }
}
