package com.demo.leave.other;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import cn.hutool.core.io.resource.ResourceUtil;

/**
 * 
 * 菜单栏
 * @author Pjh
 * @date 2021年3月1日
 */
@Path("/menu")
public class MenuController extends Controller {
    public void index() {
        renderJson(ResourceUtil.readUtf8Str("leave/menu.json"));
    }
}
