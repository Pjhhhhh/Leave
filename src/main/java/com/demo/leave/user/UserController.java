package com.demo.leave.user;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * 
 * 用户管理界面
 * @author Pjh
 * @date 2021年3月1日
 */
@Path("/user")
public class UserController extends Controller {

    public void index() {
        render("/leave/user.html");
    }
}
