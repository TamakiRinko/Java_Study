package com.swt.regextest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换，分割
 */
public class Demo03 {
    public static void main(String[] args) {
//        //表达式对象
//        Pattern pattern = Pattern.compile("([a-z]+)([0-9]+)");      //正则表达式
//        //创建Matcher对象
//        Matcher matcher = pattern.matcher("aa232**ssd445*sds233");   //要匹配的目标字符串
//
//        //替换
//        String string = matcher.replaceAll("#");    //匹配到的均替换为#
//        System.out.println(string);

        //分割
        //一般分割
        String str = "a   ,b,c";
        String[] arrs = str.split(","); //按逗号切割
        System.out.println(Arrays.toString(arrs));
        //按正则表达式切割
        String str2 = "a232asd34bg223bfb555";
        String[] arrs2 = str2.split("\\d+");    //按数字切割
        System.out.println(Arrays.toString(arrs2));
    }
}
