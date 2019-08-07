package com.swt.chat02;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室: 客户端
 * 目标: 使用多线程实现多个客户可以正常收发多条消息
 * 问题：客户端读写没有分开 必须先写后读
 */
public class TMultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Client-----");
        Socket client = new Socket("localhost", 9999);
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));	//System.in是InputStream，转换为Reader
        String msg = "";
        String getmsg = "";

        boolean isRunning = true;
        while (isRunning){
            msg = console.readLine();		//从控制台接收消息
            dos.writeUTF(msg);
            dos.flush();
            getmsg = dis.readUTF();
            System.out.println(getmsg);
        }
        dos.close();
        dis.close();
        client.close();
    }
}
