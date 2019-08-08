package com.swt.chat05;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室: 服务器
 * 目标: 加入容器实现群聊
 */
public class groupServer {
    //若又要修改，同时又要使用，则不建议使用ArrayList，使用CopyOnWriteArrayList，使用方法与ArrayList相同
    private static CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("-----Server-----");
        ServerSocket server = new ServerSocket(9999);
        while(true){
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            Channel channel = new Channel(client);
            channels.add(channel);//交给容器来管理
            new Thread(channel).start();
        }
    }


    /**
     * 一个Channel代表server为一个客户分配的对应的线程，一份资源!
     * 静态内部类，不用调用外部类，隐藏
     * 功能：接收消息，发送消息，释放资源
     */
    static class Channel implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private Boolean isRunning;
        private String name;

        /**
         * 逻辑代码在这里！！
         */
        @Override
        public void run() {
            while (isRunning){
                String msg = receive();
                if(!msg.equals("")){
//                    send(msg);
                    sentToOthers(this.name + "说: " + msg);
                }
            }
        }

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                //线程还未启动，先接受名字，欢迎到来，通知其他人！
                this.name = receive();
                this.send(this.name + "，欢迎你进入聊天室！");
                this.sentToOthers(this.name + "进入聊天室。");
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

        /**
         * 发送给对应的客户！
         * @param msg 信息
         */
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
         * 获取信息后将其转发给其他客户
         * 私聊：确定数据格式：@xxx: msg
         * @param msg 信息
         */
        private void sentToOthers(String msg){
            //取得真正的消息
            int first = msg.indexOf(":");
            String realmsg = msg.substring(first + 2);
            //判断是否为私聊
            boolean isPrivate = realmsg.startsWith("@");
            boolean flag = false;
            int idx = realmsg.indexOf(":");     //检测冒号
            if(isPrivate && idx != -1){
                String targetName = realmsg.substring(1, idx);
                for(Channel channel: channels){
                    //匹配到私聊对象
                    if(channel.name.equals(targetName)){
                        channel.send(this.name + "悄悄对您说: " + realmsg.substring(idx + 1));
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    isPrivate = false;
                }
            }else {     //格式不对
                isPrivate = false;
            }
            //群聊
            if(!isPrivate){
                for(Channel channel: channels){
                    if(channel == this){
                        continue;
                    }
                    //发送给所有其他客户
                    channel.send(msg);
                }
            }
        }

        /**
         * 释放资源
         */
        private void release(){
            //不再循环接收
            this.isRunning = false;
            SwtUtils.close(dis, dos, client);
            channels.remove(this);
            sentToOthers(this.name + "离开聊天室。");
        }

    }
}
