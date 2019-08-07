package com.swt.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 目标：实现一个客户收发信息
 */
public class Chat {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Server-----");
        // 1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        // 2、阻塞式等待连接 accept
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //读取消息
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String datas = dis.readUTF();
        //返回消息
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(datas);
        dos.flush();		//不可少！
        //释放资源
        dis.close();
        dos.close();
        client.close();

    }
}
