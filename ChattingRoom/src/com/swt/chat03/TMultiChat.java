package com.swt.chat03;

import com.swt.chat01.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室: 服务器
 * 目标: 封装使用多线程实现多个客户可以正常收发多条消息
 * 问题:
 * 1、代码不好维护
 * 2、客户端读写没有分开 必须先写后读
 */
public class TMultiChat {
    public static void main(String[] args) throws IOException {
        System.out.println("-----Server-----");
        ServerSocket server = new ServerSocket(9999);
        while(true){
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
    }


    /**
     * 一个客户代表一个Channel!
     * 静态内部类，不用调用外部类，隐藏
     * 功能：接收消息，发送消息，释放资源
     */
    static class Channel implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private Boolean isRunning;

        /**
         * 逻辑代码在这里！！
         */
        @Override
        public void run() {
            while (isRunning){
                String msg = receive();
                if(!msg.equals("")){
                    send(msg);
                }
            }
        }

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                isRunning = true;
            } catch (IOException e) {
                //出错就直接释放，没必要做下面的操作
                e.printStackTrace();
                release();
            }
        }

        private String receive(){
            String msg = "";
            try{
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
            return msg;
        }

        private void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        /**
         * 释放资源
         */
        private void release(){
            //不再循环接收
            this.isRunning = false;
            SwtUtils.close(dis, dos, client);
        }

    }
}
