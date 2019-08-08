package com.swt.chat04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装接收端
 * 1、接收消息
 * 2、释放资源
 * 3、重写run
 */
public class Receive implements Runnable{
    DataInputStream dis;
    Socket client;
    private boolean isRunning;

    public Receive(Socket client) {
        this.client = client;
        try {
            dis = new DataInputStream(client.getInputStream());
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    @Override
    public void run() {
        while (isRunning){
            String msg = receive();
            if(!msg.equals("")){
                System.out.println(msg);
            }
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

    /**
     * 释放资源
     */
    private void release(){
        this.isRunning = false;
        //不再循环接收
        SwtUtils.close(dis, client);
    }
}
