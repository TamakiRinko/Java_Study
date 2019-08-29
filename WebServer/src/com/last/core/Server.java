package com.last.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：处理404，505和首页
 */
public class Server {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;
    private boolean isRunning;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server server = new Server();
        server.start();
    }

    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(9999);
            isRunning = true;
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败!");
            this.stop();
        }
    }
    //接收连接处理
    public void receive(){
        while(isRunning){
            try {
                Socket client = serverSocket.accept();
                System.out.println("一个客户端建立了连接....");
                //多线程处理
                new Thread(new Dispatcher1(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("客户端错误");
            }
        }
    }
    //结束服务
    public void stop(){
        isRunning = false;
        try {
            this.serverSocket.close();
            System.out.println("服务器已停止!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
