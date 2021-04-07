package com.yc.springframework.context.annotation;

import com.yc.springframework.stereotype.MyComponent;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyConfiguration {
    String value() default "";
}
