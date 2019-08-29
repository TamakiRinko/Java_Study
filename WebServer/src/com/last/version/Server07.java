package com.last.version;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：整合配置文件
 * 配置文件+反射，动态增加新建
 */
public class Server07 {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server07 server07 = new Server07();
        server07.start();
    }

    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(9999);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败!");
        }
    }
    //接收连接处理
    public void receive(){
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端建立了连接....");
            //获得请求协议并分析！
            Request request = new Request(client);
            //获得响应协议！
            Response response = new Response(client);

            //使用WebXml解析，根据给出的配置文件，从url中反射对象
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());
            //放入脚本类中进行操作
            if(null != servlet){
                servlet.service(request, response);
                response.pushToBrowser(200);
            }else{
                //错误页面
                response.pushToBrowser(404);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }
    //结束服务
    public void stop(){

    }

}
