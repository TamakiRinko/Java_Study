package com.swt.regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式分组处理
 */
public class Demo02 {
    public static void main(String[] args) {
        //在这个字符串: asfsd23323adfsf22341，是否符合：\w+
        //表达式对象
        Pattern pattern = Pattern.compile("([a-z]+)([0-9]+)");      //正则表达式
        //创建Matcher对象
        Matcher matcher = pattern.matcher("aa232**ssd445*sds233");   //要匹配的目标字符串
        while (matcher.find()){
            System.out.println(matcher.group(0));   //匹配到的整个字符串：aa232
            System.out.println(matcher.group(1));   //第一个分组([a-z]+)匹配到的子串：aa
            System.out.println(matcher.group(2));   //第二个分组([0-9]+)匹配到的子串:232
            System.out.println("");
        }
    }
}
