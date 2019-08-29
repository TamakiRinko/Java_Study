package com.last.version;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 分发器：加入内容状态处理，404，505及首页
 */
public class Dispatcher1 implements Runnable{
    private Socket client;
    private Request request;
    private Response response;

    Dispatcher1(Socket client){
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
            //没有url，进入首页，两个条件均要考虑！
            if(null == request.getUrl() || request.getUrl().equals("")){
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
                byte[] error = new byte[1024];
                is.read(error);
                response.println(new String(error));
                response.pushToBrowser(200);
                is.close();
            }
            //使用WebXml解析，根据给出的配置文件，从url中反射对象
            Servlet servlet = WebApp.getServletFromUrl(request.getUrl());
            //放入脚本类中进行操作
            if(null != servlet){
                servlet.service(request, response);
                response.pushToBrowser(200);
            }else{
                //错误页面
                //多线程中通过类加载器来定位读取文件！
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
                byte[] error = new byte[1024];
                is.read(error);
                response.println(new String(error));
                response.pushToBrowser(404);
                is.close();
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
