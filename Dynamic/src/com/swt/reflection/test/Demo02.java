package com.swt.reflection.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射的API，获得类的对应的信息
 */
public class Demo02 {
    public static void main(String[] args) {
        String path = "com.swt.reflection.bean.User";
        try {
            //第一种方式
            Class clazz = Class.forName(path);
            System.out.println(clazz.getName());    //包名+类名
            System.out.println(clazz.getSimpleName());  //类名

            //获取属性信息
            Field[] fields = clazz.getFields(); //只获得public属性
            System.out.println(fields.length);
            Field[] fields1 = clazz.getDeclaredFields();    //所有属性
            System.out.println(fields1.length);
            try {
                Field f = clazz.getDeclaredField("uname");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            //获得方法信息
            Method[] methods = clazz.getDeclaredMethods();  //获得所有方法，不需要提供参数信息
            try {
                Method m01 = clazz.getDeclaredMethod("getUname", null);
                Method m02 = clazz.getDeclaredMethod("setUname", String.class);     //传递方法中参数对应的Clazz对象
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            //获得构造器信息
            Constructor[] constructors = clazz.getConstructors();
            try {
                Constructor constructorDefault = clazz.getDeclaredConstructor(null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            for(Constructor constructor: constructors){
                System.out.println("构造器: " + constructor);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
