package com.last.user;

import com.last.core.Request;
import com.last.core.Response;
import com.last.core.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">");
        response.print("<title>");
        response.print("第一个小脚本");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("欢迎回来" + request.getParameterValue("uname"));
        response.print("</body>");
        response.print("</html>");
    }
}
