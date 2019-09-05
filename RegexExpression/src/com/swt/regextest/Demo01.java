package com.swt.regextest;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本用法
 */
public class Demo01 {
    public static void main(String[] args) {
        //在这个字符串: asfsd23323adfsf22341，是否符合：\w+
        //表达式对象
        Pattern pattern = Pattern.compile("\\w+");      //正则表达式
        //创建Matcher对象
        Matcher matcher = pattern.matcher("asfsd&&23323&&adf&sf22341");   //要匹配的目标字符串
        System.out.println(matcher.matches());  //匹配整个字符串，若不匹配，停留在不匹配处：&&
        System.out.println(matcher.find());     //从上一次匹配结束位置开始，一直到找到第一个匹配的子串：23323
        System.out.println(matcher.find());     //从上一次匹配结束位置开始，一直到找到第一个匹配的子串：adf
//        System.out.println(matcher.find());     //从上一次匹配结束位置开始，一直到找到第一个匹配的子串：sf22341
//        System.out.println(matcher.find());     //从上一次匹配结束位置开始，一直到找到第一个匹配的子串：没有了！

        //之前必须进行了find或matches操作，且查找到了
        System.out.println(matcher.group());    //前一次matcher.find或matches的结果！
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
