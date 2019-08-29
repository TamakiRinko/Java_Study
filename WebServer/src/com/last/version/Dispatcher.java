package com.last.version;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable{
    private Socket client;
    private Request request;
    private Response response;

    Dispatcher(Socket client){
        this.client = client;
        try {
            //获得请求协议并分析！
            // 获得响应协议！
            request = new Request(client);
            response = new Response(client);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }
    @Override
    public void run() {
        try{
            //使用WebXml解析，根据给出的配置文件，从url中反射对象
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());
            //放入脚本类中进行操作
            if(null != servlet){
                servlet.service(request, response);
                response.pushToBrowser(200);
            }else{
                //错误页面
                response.pushToBrowser(404);
            }
        }catch (Exception e){
            try {
                response.pushToBrowser(500);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.release();     //短连接，用完释放
    }

    private void release(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
