package com.last.version;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    private BufferedWriter bw;
    private StringBuilder content;  //正文
    private StringBuilder headInfo; //协议头，包含状态行和请求头，回车信息
    private int contentLen;         //正文字节数，动态变化

    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";

    public Response(Socket client){
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            headInfo = null;
        }
    }
    public Response(){
        content = new StringBuilder();
        headInfo = new StringBuilder();
        contentLen = 0;
    }
    public Response(OutputStream os){
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    //根据状态码，构建头信息
    private void createHeadInfo(int code){
        //响应行: HTTP/1.1 200 OK
        headInfo.append("HTTP/1.1").append(BLANK);
        headInfo.append(code).append(BLANK);
        switch (code){
            case 200:
                headInfo.append("OK").append(CRLF);
            case 404:
                headInfo.append("NOT FOUND").append(CRLF);
            case 505:
                headInfo.append("SERVER ERROR").append(CRLF);
        }
    //响应头（最后有空行）
        /*
            Date:Mon,31Dec209904:25:57GMT
			Server:shsxt Server/0.0.1;charset=GBK
			Content-type:text/html
			Content-length:39725426
        */
        headInfo.append("Date:").append(new Date()).append(CRLF);
        headInfo.append("Server:").append("njswt Server/0.0.1;charset=GBK").append(CRLF);
        headInfo.append("Content-type:text/html").append(CRLF);
        headInfo.append("Content-length:").append(contentLen).append(CRLF);
        headInfo.append(CRLF);
    }

    /**
     * 构建正文信息
     * @param info
     * @return
     */
    public Response println(String info){
        content.append(info).append(CRLF);
        contentLen += (info + CRLF).getBytes().length;
        return this;
    }

    /**
     * 构建正文信息
     * @param info
     * @return
     */
    public Response print(String info){
        content.append(info);
        contentLen += info.getBytes().length;
        return this;
    }

    //推送响应信息
    public void pushToBrowser(int code) throws IOException {
        if(null == headInfo){
            code = 505;
        }
        createHeadInfo(code);
        bw.append(headInfo);
        bw.append(content);
        bw.flush();
    }
}
