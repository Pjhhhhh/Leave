package com.demo.leave.leave;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

@Path("/leave")
public class LeaveController extends Controller {
    
    public void index() {
        render("/leave/leave.html");
    }
    
}
