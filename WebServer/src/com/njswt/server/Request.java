package com.njswt.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 封装请求协议，目标：获取method uri 以及请求参数
 */
public class Request {
    public static final int DATAS_LEN = 1024 * 1024;
    private final  String CRLF = "\r\n";
    private String requestInfo;     //协议信息
    private String method;          //请求方式
    private String url;             //请求url
    private String queryStr;        //请求参数


    public Request(InputStream is){
        //获取请求协议，展示内容
        byte[] datas = new byte[DATAS_LEN];
        int len = 0;
        try {
            len = is.read(datas);
            requestInfo = new String(datas, 0, len);
            System.out.println(requestInfo);
            parseRequestInfo();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


    }
    public Request(Socket client) throws IOException {
        this(client.getInputStream());  //只能throws，因为this必须在首行
    }

    private void parseRequestInfo(){
        System.out.println("--------分解开始--------");
        System.out.println("--------1. 获取请求方式：开头到第一个/--------");
        this.method = this.requestInfo.substring(0, this.requestInfo.indexOf("/")).toLowerCase().trim();
        System.out.println(this.method);
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
        System.out.println(this.url);

        System.out.println("--------3. 获取请求参数:如果Get已经获取,如果是post可能在请求体中--------");
        if(method.equals("post")){
            //对于post，可能在url后面带参数，也可能在最后带参数！
            String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();   //找到最后的空行
            this.queryStr += ("&" + qStr);
        }
        System.out.println(method + "-->" + url + "-->" + queryStr);
    }
}
