package com.swt.chat01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 在线聊天室：客户端
 * 目标：实现一个客户收发信息
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Client-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
        Socket client = new Socket("localhost", 9999);
        //2、客户端发送消息
        //每次读取一行符合常理
        BufferedReader consule = new BufferedReader(new InputStreamReader(System.in));  //对接控制台
        String msg = consule.readLine();
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();
        //3、获取消息
		DataInputStream dis =new DataInputStream(client.getInputStream());
		String getmsg =dis.readUTF();
		System.out.println(getmsg);

		dos.close();
		dis.close();
		client.close();
    }
}
