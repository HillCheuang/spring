package com.yc.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao =new StudentDao();

    @Test
    public void add() {
        studentDao.add("张三");
    }

    @Test
    public void update() {
        studentDao.update("张三");
    }
}