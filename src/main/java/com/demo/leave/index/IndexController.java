package com.demo.leave.index;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * 
 * 主页
 * @author Pjh
 * @date 2021年2月18日
 */
@Path("/index")
public class IndexController extends Controller {
    
    public void index() {
        render("/leave/index.html");
    }
}
