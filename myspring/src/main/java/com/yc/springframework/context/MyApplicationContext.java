package com.yc.springframework.context;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:39
 */
public interface MyApplicationContext {

    public Object getBean(String id) throws  RuntimeException;
}
