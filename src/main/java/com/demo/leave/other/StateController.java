package com.demo.leave.other;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import cn.hutool.core.io.resource.ResourceUtil;

/**
 * 
 * 申请状态类型
 * @author Pjh
 * @date 2021年2月19日
 */
@Path("/state")
public class StateController extends Controller {
    
    public void index() {
        renderJson(ResourceUtil.readUtf8Str("leave/state.json"));
    }
}
