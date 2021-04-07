package com.yc.dao;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-04 14:14
 */
public class StudentDao {

    public int add(String name){
        System.out.println("添加学生："+name);
        Random random =new Random();
        return random.nextInt();
    }


    public void update(String name){
        System.out.println("更新学生："+name);

    }
}
