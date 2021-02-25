package com.demo.leave.start;

import com.jfinal.server.undertow.UndertowServer;

public class Leave {
    
    public static void main(String[] args) {
        UndertowServer.start(LeaveConfig.class);
    }
    
}
