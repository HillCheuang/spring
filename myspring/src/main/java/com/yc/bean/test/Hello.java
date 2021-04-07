package com.yc.bean.test;

import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestory;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 13:38
 */
@MyComponent
public class Hello {

    public Hello(){
        System.out.println(1);
    }

    @MyPostConstruct
    public void setup(){
        System.out.println("PostConstruct");
    }

    @MyPreDestory
    public void destory(){
        System.out.println("MyPreDestory");
    }

    @MyBean
    public Random r(){
        return new Random();
    }

    public void say(){
        System.out.println("hello");
    }
}
