package com.yc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-04 15:11
 */
@Configuration  //表示当前类是配置类
@ComponentScan(basePackages = "com.yc")
public class AppConfig {
}
