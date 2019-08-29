package com.last.user;

import com.last.core.Request;
import com.last.core.Response;
import com.last.core.Servlet;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        //关注业务逻辑
        String uname = request.getParameterValue("uname");
        String[] friends = request.getParameterValues("fav");
        response.print("<html>");
        response.print("<head>");
        //response中告诉浏览器字符集，防止乱码！
        response.print("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">");
        response.print("<title>");
        response.print("注册成功");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.println("你的名字为:" + uname);
        response.println("你的朋友为:");
        for(String v: friends){
            if(v.equals("0")){
                response.println("AAA");
            }
            else if(v.equals("1")){
                response.println("BBB");
            }
            else if(v.equals("2")){
                response.println("CCC");
            }
        }
        response.print("</body>");
        response.print("</html>");
    }
}
