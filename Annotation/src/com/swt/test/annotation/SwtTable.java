package com.swt.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//通常增加两个元注解，对注解进行注释
@Target(value = {ElementType.TYPE})     //只能修饰类型，用于类和表的转化
@Retention(RetentionPolicy.RUNTIME)
public @interface SwtTable {
    String value();
}
