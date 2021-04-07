package com.yc.config;

import com.yc.bean.HelloWorld;
import com.yc.springframework.context.annotation.MyCompentScan;
import com.yc.springframework.context.annotation.MyConfiguration;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:45
 */
@MyConfiguration
@MyCompentScan(basePackages = {"com.yc.bean","com.yc.config"})
public class AppConfig {
}
