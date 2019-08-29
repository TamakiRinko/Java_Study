package com.last.user;

import com.last.core.Request;
import com.last.core.Response;
import com.last.core.Servlet;

public class OthersServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        System.out.println("OtherServlet");
    }
}
