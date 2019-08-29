package com.last.version;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * 封装请求协议，目标：封装请求参数为Map
 */
public class Request {
    public static final int DATAS_LEN = 1024 * 1024;
    private final  String CRLF = "\r\n";
    private String requestInfo;     //协议信息
    private String method;          //请求方式
    private String url;             //请求url
    private String queryStr;        //请求参数
    private Map<String, List<String>> paramaterMap;     //可能有重名的，用list


    public Request(InputStream is){

        paramaterMap = new HashMap<>();

        //获取请求协议，展示内容
        byte[] datas = new byte[DATAS_LEN];
        int len = 0;
        try {
            len = is.read(datas);
            requestInfo = new String(datas, 0, len);
            parseRequestInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Request(Socket client) throws IOException {
        this(client.getInputStream());  //只能throws，因为this必须在首行
    }

    private void parseRequestInfo(){
        System.out.println("--------分解开始--------");
        System.out.println("--------1. 获取请求方式：开头到第一个/--------");
        this.method = this.requestInfo.substring(0, this.requestInfo.indexOf("/")).toLowerCase().trim();
        System.out.println("--------2. 获取请求url：第一个/到HTTP/--------");
        System.out.println("--------可能包含请求参数: ? 则前面的为url，需再分割--------");
//        1）获取/位置
        int startIdx = this.requestInfo.indexOf("/") + 1;
//        2）获取HTTP位置
        int endIdx = this.requestInfo.indexOf("HTTP/");
//        3）分割字符串
        this.url = this.requestInfo.substring(startIdx, endIdx).trim();
        this.queryStr = "";
//        4）获取?的位置
        int queryIdx = this.url.indexOf("?");
        if(queryIdx >= 0){
            //存在请求参数
            String[] urlArray = this.url.split("\\?");  //正则表达式，需转义
            this.url = urlArray[0];
            this.queryStr = urlArray[1];
        }
//        System.out.println(this.url);

        System.out.println("--------3. 获取请求参数:如果Get已经获取,如果是post可能在请求体中--------");
        if(method.equals("post")){
            //对于post，可能在url后面带参数，也可能在最后带参数！
            String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();   //找到最后的空行
            this.queryStr += ("&" + qStr);
        }
        System.out.println(method + "-->" + url + "-->" + queryStr);

        //转成Map
        //结构：fav=1&fav=2&uname=njswt&age=21&others=
        convertMap();
    }

    private void convertMap(){
        //分割字符串
        String[] keyValues = this.queryStr.split("&");
        for(String string: keyValues){
            //再次分割
            String[] kv = string.split("=");
            kv = Arrays.copyOf(kv, 2);  //保证kv的长度为2，有两个内容
            //获取key和value
            String key = kv[0];
            String value = kv[1]==null?null:decode(kv[1],"utf-8");  //处理中文
            //存储到Map中
            if(!paramaterMap.containsKey(key)){
                //没有，则新添一个键值对，否则直接加入
                paramaterMap.put(key, new ArrayList<>());
            }
            paramaterMap.get(key).add(value);
        }
    }

    /**
     * 处理中文
     * @return
     */
    private String decode(String value,String enc) {
        try {
            return java.net.URLDecoder.decode(value, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过name获取对应的多个值
     * @param key
     * @return
     */
    public String[] getParameterValues(String key){
        List<String> values = this.paramaterMap.get(key);
        if(null == values || values.size() < 1){
            return null;
        }
        return values.toArray(new String[0]);
    }

    /**
     * 通过name获取对应的一个值
     * @param key
     * @return
     */
    public String getParameterValue(String key){
        String[] values = getParameterValues(key);
        return values == null?null:values[0];
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getQueryStr() {
        return queryStr;
    }
}

