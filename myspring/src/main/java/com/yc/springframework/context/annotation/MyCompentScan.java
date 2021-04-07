package com.yc.springframework.context.annotation;

import com.yc.springframework.stereotype.MyComponent;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:
 * @author: HillCheung
 * @create: 2021-04-05 11:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyCompentScan {
    Class<?>[] basePackageClasses() default {};

    String [] basePackages() default  "";
}
