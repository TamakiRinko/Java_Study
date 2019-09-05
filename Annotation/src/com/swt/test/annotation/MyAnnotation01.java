package com.swt.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//通常增加两个元注解，对注解进行注释
@Target(value = {ElementType.METHOD, ElementType.TYPE})     //能修饰方法，类型等
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation01 {
    String studentName() default "";
    int id() default -1;
    int age() default 0;
    String[] school() default {"aaa", "bbb"};
}
