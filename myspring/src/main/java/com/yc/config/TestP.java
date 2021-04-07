package com.yc.config;

import com.yc.bean.test.Hello;
import com.yc.springframework.beans.factory.anntaion.MyAutowired;
import com.yc.springframework.stereotype.MyComponent;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 18:40
 */
@MyComponent
public class TestP {
    @MyAutowired
    public Hello hello;


    public void sayOne(){
        System.out.println("Test");
    }
}
