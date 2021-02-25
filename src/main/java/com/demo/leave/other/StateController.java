package com.demo.leave.other;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import cn.hutool.core.io.resource.ResourceUtil;

@Path("/state")
public class StateController extends Controller {
    
    public void index() {
        renderJson(ResourceUtil.readUtf8Str("leave/state.json"));
    }
    
}
