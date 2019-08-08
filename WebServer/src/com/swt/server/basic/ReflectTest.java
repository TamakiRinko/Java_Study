package com.swt.server.basic;

import java.lang.reflect.InvocationTargetException;

/**
 * 反射: 把java类中的各种结构(方法、属性、构造器、类名)映射成一个个的Java对象。
 * 1、获取Class对象，三种方式
 * 2、可以动态创建对象推荐：clz.getConstructor().newInstance()
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //三种方式
        //1、对象.getClass()
        Iphone iphone = new Iphone();
        //2、类.class()
        Class clz = iphone.getClass();
        clz = Iphone.class;
        //3、Class.forName("包名.类名")
        //推荐使用第三种！
        clz = Class.forName("com.swt.server.basic.Iphone");


        //动态创建对象
        Iphone iphone1 = (Iphone) clz.newInstance();
        System.out.println(iphone1);

        //推荐使用这种！先拿到构造器，再构造
        Iphone iphone2 = (Iphone) clz.getConstructor().newInstance();
        System.out.println(iphone2);

    }
}

class Iphone{
    public Iphone() {

    }
}