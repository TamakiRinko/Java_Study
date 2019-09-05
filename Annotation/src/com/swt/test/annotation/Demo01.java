package com.swt.test.annotation;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo01 /*extends Object*/{

    @Override
    //此即注解，表明这个方法重写了父类方法，编译器会查看！
    public String toString(){
        return "";
    }

    @Deprecated
    public static void test01(){
        System.out.println("Deprecated");
    }

    public static void test02(){
        List list = new ArrayList();
    }

    public static void main(String[] args) {
        Date x;
        test01();
        test02();
    }
}
