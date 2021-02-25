package com.demo.leave.login;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

@Path("/login")
public class LoginController extends Controller {
    
    public void index() {
        render("/leave/login.html");
    }
    
}
