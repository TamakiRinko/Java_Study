package com.njswt.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标: 封装响应信息
 * 1. 内容可以动态添加
 * 2. 关注状态码，给出状态码，拼接好响应的协议信息
 */
public class Server04 {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server04 server04 = new Server04();
        server04.start();
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
            Request2 request2 = new Request2(client);
            //获得响应协议！
            ResponseInfo(client, request2);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误");
        }
    }
    //结束服务
    public void stop(){

    }

    /**
     *  构造响应协议
     */
    public void ResponseInfo(Socket client, Request2 request){
        Response response = new Response(client);
        //关注正文内容
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("服务器响应成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("njswt server终于回来了。。。。" + request.getParameterValue("uname"));
        response.print("</body>");
        response.print("</html>");
        //关注状态码
        try {
            response.pushToBrowser(200);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端错误！");
        }
    }

}
