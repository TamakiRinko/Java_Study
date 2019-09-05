package com.swt.dynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Timer;

public class Demo01 {
    public static void main(String[] args) {
//        //通过IO流将字符串存储为一个临时文件，然后调用编译方法
//        String str = "public class HelloWorld{\n" +
//                "    public static void main(String[] args) {\n" +
//                "        System.out.println(\"Hello swt!\");\n" +
//                "    }\n" +
//                "}";
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        int result = compiler.run(null, null, null, "HelloWorld.java");
//        System.out.println(result == 0?"编译成功":"编译失败");

        //通过Runtime来运行已编译好的类
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process process = runtime.exec("java HelloWorld");
//            InputStream inputStream = process.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            String info = "";
//            while ((info = reader.readLine()) != null){
//                System.out.println(info);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            URL[] urls = new URL[]{
                    new URL("file:/" + "D:/NJU_Study/Github/Java_Study/Dynamic/")
            };
            URLClassLoader loader = new URLClassLoader(urls);
            Class c = loader.loadClass("HelloWorld");
            //调用加载类的main方法
            Method m = c.getMethod("main", String[].class);
            //强转不可少，否则自动转换为"aa", "bb"
            // 即:m.invoke(null, new String[]{"aa", "bb"});被转换为: m.invoke(null, "aa", "bb");
            // 参数个数不同!
            m.invoke(null, (Object)new String[]{"aa", "bb"}); //静态方法不需要指定哪个对象
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
