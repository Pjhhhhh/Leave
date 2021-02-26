package com.demo.leave.out;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Ret;

/**
 * 退出登录
 * @author Pjh
 * @date 2021年2月23日
 */
@Path("/leave/out")
public class OutController extends Controller {
    
    /**
     * 退出登录时清除cookie
     */
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
