package com.swt.chat03;

import java.io.Closeable;
import java.io.IOException;

/**
 * 工具类
 */
public class SwtUtils {
    /**
     * 释放资源
     */
    public static void close(Closeable... targets){
        for(Closeable target: targets){
            try {
                if(null != target){
                    target.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
