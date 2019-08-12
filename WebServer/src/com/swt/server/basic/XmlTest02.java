package com.swt.server.basic;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 熟悉SAX解析流程
 * 使用Persons列表，将解析出的元素填入对象内
 */
public class XmlTest02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //SAX解析
        //1、获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2、从解析工厂获取解析器
        SAXParser parse = factory.newSAXParser();
        //3、编写处理器
        //4、加载文档 Document 注册处理器
        PersonHandler handler = new PersonHandler();
        //5、解析
        parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/swt/server/basic/p.xml")
                ,handler);//从当前线程的类加载器中获取

        //获取解析的数据
        List<Person> personList = handler.getPersons();
        for(Person person: personList){
            System.out.println(person.getName() + "---->" + person.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler{
    private List<Person> persons;
    private Person person;
    private String tag; //存储操作的标签
    @Override
    public void startDocument() throws SAXException {
        System.out.println("-----解析文档开始-----");
        persons = new ArrayList<Person>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("-----解析文档结束-----");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //只关注qName
        System.out.println(qName + "-->解析开始");
        if(qName != null){
            tag = qName;
            if (tag.equals("person")){
                person = new Person();
                persons.add(person);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束");
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
        if(tag != null){
            if(tag.equals("name")){
                person.setName(contents);
            }else if(tag.equals("age")){
                person.setAge(Integer.valueOf(contents));
            }
        }

    }

    public List<Person> getPersons() {
        return persons;
    }
}
