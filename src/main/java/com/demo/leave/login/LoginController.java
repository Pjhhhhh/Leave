package com.demo.leave.login;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * 用户登录界面
 * @author Pjh
 * @date 2021年2月18日
 */
@Path("/login")
public class LoginController extends Controller {
    
    public void index() {
        render("/leave/login.html");
    }
}
