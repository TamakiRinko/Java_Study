package com.last.core;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器
 */
public class WebHandler extends DefaultHandler {
    private List<Entity> entityList;
    private List<Mapping> mappingList;
    private Entity entity;
    private Mapping mapping;
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
