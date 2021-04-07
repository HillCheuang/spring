package com.yc.test;

import com.yc.MyAppConfig;
import com.yc.bean.HelloWorld;
import com.yc.biz.StudentBizImpl;
import com.yc.springframework.context.MyApplicationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

import java.net.MalformedURLException;

/**
 * @program: SpringDemo
 * @description:
 * @create: 2021-04-05 14:22
 */

public class HelloWorldTest {
    public static void main(String[] args) throws MalformedURLException {
        MyApplicationContext ac = new MyApplicationConfigApplicationContext(MyAppConfig.class);
        StudentBizImpl studentBizImpl = (StudentBizImpl)ac.getBean("studentBizImpl");
        System.out.println(studentBizImpl.getStudentDAO());
    }
}
