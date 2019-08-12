package com.swt.server.basic.servlet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * 熟悉SAX解析流程
 * 使用Persons列表，将解析出的元素填入对象内
 */
public class XmlTest02 {
    public static void main(String[] args) throws Exception {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        WebHandler handler = new WebHandler();
        //5、解析
        parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/swt/server/basic/servlet/web.xml")
                ,handler);//从当前线程的类加载器中获取

        //获取解析的数据
        WebContext webContext = new WebContext(handler.getEntityList(), handler.getMappingList());
        //假设输入了……/login
        String className = webContext.getClz("/login");     //若是输入不正确的，则空指针，页面不存在，即404
        Class clz = Class.forName(className);
        Servlet servlet = (Servlet)clz.getConstructor().newInstance();
        System.out.println(servlet);
        servlet.service();
//        List<Entity> entityList = handler.getEntityList();
//        List<Mapping> mappingList = handler.getMappingList();
//        for(Entity entity: entityList){
//            System.out.println(entity.getName() + "---->" + entity.getClz());
//        }
//        System.out.println("");
//        for (Mapping mapping:mappingList){
//            System.out.println(mapping.getName());
//            for(String pattern: mapping.getPatterns()){
//                System.out.print(pattern + " ");
//            }
//            System.out.println("");
//        }
    }
}

class WebHandler extends DefaultHandler{
    private List<Entity> entityList;
    private List<Mapping> mappingList;
    private Entity entity;
    private  Mapping mapping;
    private String tag; //存储操作的标签
    private boolean isMapping;
    @Override
    public void startDocument() throws SAXException {
//        System.out.println("-----解析文档开始-----");
        entityList = new ArrayList<Entity>();
        mappingList = new ArrayList<Mapping>();
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("-----解析文档结束-----");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //只关注qName
//        System.out.println(qName + "-->解析开始");
        if(qName != null){
            tag = qName;
            if (tag.equals("servlet")){
                entity = new Entity();
                entityList.add(entity);
                isMapping = false;
            }else if(tag.equals("servlet-mapping")){
                mapping = new Mapping();
                mappingList.add(mapping);
                isMapping = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println(qName + "-->解析结束");
        tag = null;
    }

    /**
     * 处理每个标签对内的内容，每次的内容为该标签到下一个标签间隔内的内容！
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length).trim();     //去除首末的空白符！
//        System.out.println("contents = " + contents);
//        System.out.println("tag = " + tag);
        if(tag != null){
            if(isMapping){  //操作servlet-mapping
                if(tag.equals("servlet-name")){
                    mapping.setName(contents);
                }else if(tag.equals("url-pattern")){
                    mapping.addPattern(contents);
                }
            }else{          //操作servlet
                if(tag.equals("servlet-name")){
                    entity.setName(contents);
                }else if(tag.equals("servlet-class")){
                    entity.setClz(contents);
                }
            }
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }
}
