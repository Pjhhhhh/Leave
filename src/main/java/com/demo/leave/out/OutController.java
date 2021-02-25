package com.demo.leave.out;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Ret;

@Path("/leave/out")
public class OutController extends Controller {
    
    public void index() {
        removeCookie("username");
        removeCookie("perid");
        removeSessionAttr("perid");
        removeSessionAttr("ip");
        removeCookie("sessionId");
        removeCookie("JSESSIONID");
        renderJson(Ret.ok());
    }
    
}
