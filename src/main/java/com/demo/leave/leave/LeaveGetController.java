package com.demo.leave.leave;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

@Path("/leave/get")
@Before(JdbcValidator.class)
public class LeaveGetController extends Controller {
    
    public void index() {
        String username = getCookie("username");
        if (StrUtil.isNotBlank(username)) {
            String today= DateUtil.today();
            Kv cond = Kv.by("username", username);
            Record a = Db.template("leave.login", cond).findFirst();
            String name = a.getStr("user_name");
            String dept = a.getStr("dept");
            String post = a.getStr("post");
            Kv data = Kv.by("date", today).set("name", name).set("dept", dept).set("post", post);
            renderJson(Ret.ok("data", data));
        } else {
            renderError(404, new JsonRender(Ret.fail()));
        }
    }
    
}
