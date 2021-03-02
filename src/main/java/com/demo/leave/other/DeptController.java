package com.demo.leave.other;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import cn.hutool.core.io.resource.ResourceUtil;

/**
 * 
 * @author Pjh
 * @date 2021年3月1日
 */
@Path("/dept")
public class DeptController extends Controller {

    public void index() {
        renderJson(ResourceUtil.readUtf8Str("leave/dept.txt"));
    }
}
