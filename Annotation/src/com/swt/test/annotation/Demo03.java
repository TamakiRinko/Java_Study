package com.swt.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射读取注解的信息，模拟处理注解信息的流程
 */
public class Demo03 {

    public static void main(String[] args) {
        try {
            //获得了这个类的全部信息，包括注解
            Class clazz = Class.forName("com.swt.test.annotation.Student");
            Annotation[] annotations = clazz.getAnnotations();

            //获得 类 的所有注解
            for(Annotation annotation:annotations){
                System.out.println(annotation);
            }
            //获得类指定注解的值
            SwtTable st = (SwtTable)clazz.getAnnotation(SwtTable.class);
            System.out.println(st.value());
            //获得类的属性的注解
            Field f = clazz.getDeclaredField("studentName");
            SwtField swtField = f.getAnnotation(SwtField.class);
            System.out.println(swtField.columnName() + "--" + swtField.type() + "--" + swtField.length());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
