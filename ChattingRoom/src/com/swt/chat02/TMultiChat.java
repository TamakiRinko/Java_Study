package com.swt.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * 在线聊天室: 服务器
 * 目标: 使用多线程实现多个客户可以正常收发多条消息
 * 问题: 代码不好维护
 */
public class TMultiChat {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Server-----");
        ServerSocket server = new ServerSocket(9999);
        while (true){
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");

            //利用lambda表达式，比较难以维护封装
            new Thread(()->{
                boolean isRunning = true;
                //耦合使用！
                try{
                    try(DataInputStream dis = new DataInputStream(client.getInputStream());
                        DataOutputStream dos = new DataOutputStream(client.getOutputStream())) {
                        //读取消息
                        while (isRunning){
                            String datas = dis.readUTF();		//从流中读取字符串
                            //返回消息
                            dos.writeUTF(datas);
                            dos.flush();		//不可少！
                        }
                    }
                    finally {
                        //在此处关闭与某个客户端client的连接
                        client.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
