package com.last.version;

public class OthersServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        System.out.println("OthersServlet");
    }
}
