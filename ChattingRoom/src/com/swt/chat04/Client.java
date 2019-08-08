package com.swt.chat04;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 在线聊天室: 客户端
 * 目标: 加入容器实现群聊
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Client-----");
        Scanner in = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = in.nextLine();
        Socket client = new Socket("localhost", 9999);
        new Thread(new Send(client, name)).start();
        new Thread(new Receive(client)).start();
    }
}
