package com.yc.bean;

import com.yc.bean.test.Hello;
import com.yc.springframework.beans.factory.anntaion.MyAutowired;
import com.yc.springframework.beans.factory.anntaion.MyResource;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestory;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:43
 */
@MyComponent
public class HelloWorld {

//    @MyResource
    @MyAutowired
    public Hello hello;

    public HelloWorld(){
        System.out.println("hello world");
    }


    @MyBean
    public Random rd(){
        return new Random();
    }

    @MyPostConstruct
    public void setup(){
        System.out.println("PostConstruct");
    }

    @MyPreDestory
    public void destory(){
        System.out.println("MyPreDestory");
    }


    public void say(){
        System.out.println("say hello");
    }
}
