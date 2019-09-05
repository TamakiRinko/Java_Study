package com.swt.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明属性的特性
 */
@Target(value = ElementType.FIELD)     //能修饰方法，类型等
@Retention(RetentionPolicy.RUNTIME)
public @interface SwtField {
    //这些只针对一个类的属性！
    String columnName();
    String type();
    int length();
}
