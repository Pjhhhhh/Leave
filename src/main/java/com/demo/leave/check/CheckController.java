package com.demo.leave.check;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * 
 * 审核表
 * @author Pjh
 * @date 2021年2月18日
 */
@Path("/check")
@Before(CheckValidator.class)
public class CheckController extends Controller {
    
    public void index() {
        render("/leave/check.html");
    }
}
