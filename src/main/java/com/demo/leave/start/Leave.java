package com.demo.leave.start;

import com.demo.leave.config.LeaveConfig;
import com.jfinal.server.undertow.UndertowServer;

/**
 * 
 * 启动类
 * @author Pjh
 * @date 2021年2月18日
 */
public class Leave {
    
    /**
     * 启动入口，运行此   main 方法可以启动项目
     */
    public static void main(String[] args) {
        UndertowServer.start(LeaveConfig.class);
    }
}
