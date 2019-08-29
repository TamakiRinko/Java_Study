package com.last.version;

/**
 * 服务器小脚本接口
 * 表示该服务要做的事
 */
public interface Servlet {
    public void service(Request request, Response response);
}
