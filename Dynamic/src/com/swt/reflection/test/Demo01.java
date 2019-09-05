package com.swt.reflection.test;

import java.io.PrintWriter;

/**
 * 各种数据类型：
 * class，interface，enum，annotation，primitive type，void
 */
public class Demo01 {
    public static void main(String[] args) {
        String path = "com.swt.reflection.bean.User";
        try {
            //第一种方式
            Class clazz = Class.forName(path);
            //对象表示或封装一些数据，一个类加载后，JVM会创建一个对应类的Class对象
            //类的整体结构信息会放到对于的Class对象中。
            //这个Class对象就像是一面镜子，通过它能够看到类的全部信息
            System.out.println(clazz.hashCode());
            Class clazz2 = Class.forName(path);
            //一个类只对应一个Class对象
            System.out.println(clazz2.hashCode());  //同一个对象，同一个类只会被加载一次

            //第二，三种方式
            Class strClazz = String.class;
            Class strClazz2 = path.getClass();  //对象.getClass，path是字符串对象
            System.out.println(strClazz == strClazz2);

            Class intClazz = int.class;

            int[] arr01 = new int[10];
            int[] arr02 = new int[30];  //类型维数相同，为同一个Class对象
            System.out.println(arr01.getClass().hashCode() == arr02.getClass().hashCode());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
