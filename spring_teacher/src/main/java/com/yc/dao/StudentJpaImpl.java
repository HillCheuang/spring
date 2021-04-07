package com.yc.dao;

import com.yc.springframework.stereotype.MyReposetory;

import java.util.Random;

/**
 * @program: SpringDemo
 * @description:
 * @create: 2021-04-04 14:45
 */
//@Component
@MyReposetory//异常转换从 Exception 自动转为runtimeExcetion
public class StudentJpaImpl implements StudentDAO {

    @Override
    public int add(String name) {
        System.out.println("jpa 添加学生" + name);
        Random random = new Random();
        return random.nextInt();
    }

    @Override
    public void update(String name) {
        System.out.println("jpa 修改学生" + name);
    }
}
