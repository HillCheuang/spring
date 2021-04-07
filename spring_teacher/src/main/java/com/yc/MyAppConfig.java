package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: SpringDemo
 * @description:
 * @author: 阙霞
 * @create: 2021-04-05 14:32
 */
@MyConfiguration
@MyComponentScan(basePackageClasses = "com.yc")
public class MyAppConfig {
    @MyBean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
