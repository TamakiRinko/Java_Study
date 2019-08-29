package com.njswt.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;
import java.util.Date;

/**
 * 目标: 返回响应协议
 */
public class Server02 {
    public static final int DATAS_LEN = 1024 * 1024;
    private ServerSocket serverSocket;
    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public static void main(String[] args) {
//        this.start();       //无法在static中使用！要新建对象！
        Server02 server02 = new Server02();
        server02.start();
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
            StringBuilder responseInfo = ResponseInfo();
            //写出到客户端
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(responseInfo.toString());
            bw.flush();
//            OutputStream os = client.getOutputStream();
//            os.write(responseInfo.toString().getBytes());
//            os.flush();

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
    public StringBuilder ResponseInfo(){
        //1. 响应行: HTTP/1.1 200 OK
        //2. 响应头（最后存在空行）
        //3. 正文
        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<head>");
        content.append("<title>");
        content.append("服务器响应成功");
        content.append("</title>");
        content.append("</head>");
        content.append("<body>");
        content.append("njswt server终于回来了。。。。");
        content.append("</body>");
        content.append("</html>");
        int len = content.toString().getBytes().length;//必须获取字节长度
        StringBuilder responseInfo = new StringBuilder();

        //响应行: HTTP/1.1 200 OK
        responseInfo.append("HTTP/1.1").append(BLANK);
        responseInfo.append(200).append(BLANK);
        responseInfo.append("OK").append(CRLF);

        //响应头（最后有空行）
        /*
            Date:Mon,31Dec209904:25:57GMT
			Server:shsxt Server/0.0.1;charset=GBK
			Content-type:text/html
			Content-length:39725426
        */
        responseInfo.append("Date:").append(new Date()).append(CRLF);
        responseInfo.append("Server:").append("njswt Server/0.0.1;charset=GBK").append(CRLF);
        responseInfo.append("Content-type:text/html").append(CRLF);
        responseInfo.append("Content-length:").append(len).append(CRLF);
        responseInfo.append(CRLF);

        //正文
        responseInfo.append(content.toString());

        System.out.println(responseInfo);

        return responseInfo;
    }

}
