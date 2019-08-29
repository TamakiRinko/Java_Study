package com.last.core;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {
    private static WebContext webContext;

    static{
        try{
            //SAX解析
            //1、获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2、从解析工厂获取解析器
            SAXParser parse = factory.newSAXParser();
            //3、编写处理器
            //4、加载文档 Document 注册处理器
            WebHandler handler = new WebHandler();
            //5、解析
            parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/last/core/web.xml")
                    ,handler);//从当前线程的类加载器中获取

            //获取解析的数据
            webContext = new WebContext(handler.getEntityList(), handler.getMappingList());
        }
        catch (Exception e){
            System.out.println("解析配置文件错误");
        }
    }

    /**
     * 通过url获取配置文件对应的servlet对象
     * @param url
     * @return
     */
    public static Servlet getServletFromUrl(String url){
        //假设输入了……/url
        String className = webContext.getClz("/" + url);     //若是输入不正确的，则空指针，页面不存在，即404
        Class clz = null;
        try {
            clz = Class.forName(className);
            Servlet servlet = (Servlet)clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
}
