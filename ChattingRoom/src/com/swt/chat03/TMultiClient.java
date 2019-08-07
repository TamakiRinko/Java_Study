package com.swt.chat03;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室: 客户端
 * 目标: 封装使用多线程实现多个客户可以正常收发多条消息
 * 同时使得读写分离，同样使用多线程
 */
public class TMultiClient {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Client-----");
        Socket client = new Socket("localhost", 9999);
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
