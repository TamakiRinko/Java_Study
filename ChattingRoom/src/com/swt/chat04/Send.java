package com.swt.chat04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 使用多线程封装发送端
 * 1、发送消息
 * 2、从控制台获取消息
 * 3、释放资源
 * 4、重写run
 */
public class Send implements Runnable{
    private BufferedReader console;
    private DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;

    public Send(Socket client, String name){
        this.name = name;
        this.client = client;
        console = new BufferedReader(new InputStreamReader(System.in));
        try {
            dos = new DataOutputStream(client.getOutputStream());
            //发送名称
            sendToServer(name);
            isRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }

    @Override
    public void run() {
        while (isRunning){
            String msg = getStrFromConsole();
            if(!msg.equals("")){
                sendToServer(msg);
            }
        }
    }

    /**
     * 从控制台获得消息
     * @return
     */
    private String getStrFromConsole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获得失败，返回空，不用结束
        return "";
    }

    private void sendToServer(String msg){
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
        this.isRunning = false;
        //不再循环接收
        SwtUtils.close(dos, client);
    }
}
