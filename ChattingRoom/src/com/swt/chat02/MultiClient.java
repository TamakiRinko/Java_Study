package com.swt.chat02;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室: 客户端
 * 实现多个客户收发消息
 * client端不变
 */
public class MultiClient {
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
