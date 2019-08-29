package com.last.version;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 目标：加入了servlet，解耦了业务代码
 */
public class Server06 {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server06 server06 = new Server06();
        server06.start();
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
            //放入脚本类中进行操作
            Servlet servlet = null;
            if(request.getUrl().equals("login")){
                servlet = new LoginServlet();
            }else if(request.getUrl().equals("reg")){
                servlet = new RegisterServlet();
            }else{  //首页

            }
            servlet.service(request, response);
            //关注状态码
            try {
                response.pushToBrowser(200);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("客户端错误！");
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
