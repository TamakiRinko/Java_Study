package com.swt.webspider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬取链接
 */
public class Core {
    public static String getURLContent(String urlStr, String charSet){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charSet));
            String temp = "";
            while ((temp = reader.readLine()) != null){
                stringBuilder.append(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static List<String> getMatcherSubstrs(String content, String regexStr){
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()){
            list.add(matcher.group(1));
        }
        return list;
    }

    public static void main(String[] args) {
        String urlStr = "https://www.163.com/";
        String content = getURLContent(urlStr, "gbk");
        List<String> list = getMatcherSubstrs(content, "href=\"(https?.+?)\"");
        for(String str: list){
            System.out.println(str);
        }
    }
}
