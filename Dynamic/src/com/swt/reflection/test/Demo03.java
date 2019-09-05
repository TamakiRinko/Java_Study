package com.swt.reflection.test;
import com.swt.reflection.bean.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用反射的API，获得类的对应的信息
 */
public class Demo03 {
    public static void main(String[] args) {
        String path = "com.swt.reflection.bean.User";
        try {
            Class clazz = Class.forName(path);
            //通过反射API调用构造方法，构造对象
            User u = (User) clazz.newInstance();    //调用User的无参构造方法
            System.out.println(u);

            Constructor<User> c = clazz.getDeclaredConstructor(int.class, int.class, String.class);
            User u2 = c.newInstance(1001, 18, "AAA");   //Constructor对象也可以创造对象
            System.out.println(u2.getUname());

            //通过反射API调用普通方法
            User u3 = (User) clazz.newInstance();
            //获得方法，这个方法是针对整个类的，传入特定的对象调用该对象的该方法
            Method method = clazz.getDeclaredMethod("setUname", String.class);  //方法名也是参数，可以后期传入，实现动态!
            method.invoke(u3, "BBB");
            System.out.println(u3.getUname());

            //通过反射API操作属性
            //同样是针对一个类的，不是特定的对象的属性
            User u4 = (User) clazz.newInstance();
            Field f = clazz.getDeclaredField("uname");
            f.setAccessible(true);  //禁用安全检查，使得能够访问私有属性，也会提高反射效率
            //通过反射直接写属性
            f.set(u4, "CCC");   //没有上一句，不可以直接操作私有属性！
            //通过反射直接读属性
            System.out.println(f.get(u4));
            System.out.println(u4.getUname());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
