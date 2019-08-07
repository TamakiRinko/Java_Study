package com.swt.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室: 服务器
 * 目标: 实现多个客户可以收发消息
 * 问题: 其他客户必须等待之前的客户退出，才能继续 排队
 */
public class MultiChat {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Server-----");
        ServerSocket server = new ServerSocket(9999);
        while (true){
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            DataInputStream dis = new DataInputStream(client.getInputStream());

            //读取消息
            boolean isRunning = true;
            while (isRunning){
                String datas = dis.readUTF();		//从流中读取字符串
                //返回消息
                dos.writeUTF(datas);
                dos.flush();		//不可少！
            }
            dis.close();
            dos.close();
            client.close();
        }
    }
}
