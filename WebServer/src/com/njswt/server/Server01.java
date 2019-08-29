package com.njswt.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标: 使用ServerSocket建立与浏览器的连接，获取请求协议
 */
public class Server01 {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server01 server01 = new Server01();
        server01.start();
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
//            Request request = new Request(client);
            Request2 request2 = new Request2(client);
//            String[] strings = request2.getParameterValues("pwd");
////            for(String string:strings){
////                System.out.println(string);
////            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }
    //结束服务
    public void stop(){

    }
}
