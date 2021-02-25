package com.demo.leave.leave;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.hutool.core.util.IdUtil;

@Path("/leave/jdbc")
@Before(JdbcValidator.class)
public class LeaveJdbcController extends Controller {
    
    public void index() {
        String uuid = IdUtil.simpleUUID();
        String date = getPara("date");
        String date1 = getPara("date1");
        String name = getPara("name");
        String dept = getPara("dept");
        String post = getPara("post");
        String reason = getPara("reason");
        String type = getPara("type");
        String day = getPara("day");
        String hour = getPara("hour");
        String state = "未审核";
        Record user = new Record().set("uuid", uuid).set("date", date).set("date1", date1).set("name", name).set("dept", dept)
                .set("post", post).set("reason", reason).set("type", type).set("day", day)
                .set("hour", hour).set("state", state).set("opinion", state);
        Db.save("leave_form", "uuid", user);
        Kv data = Kv.by("uuid", uuid).set("date", date).set("date1", date1).set("name", name).set("dept", dept).set("post", post)
                .set("reason", reason).set("type", type).set("day", day).set("hour", hour)
                .set("state", state).set("opinion", state);
        renderJson(Ret.ok("data", data));
    }
    
}
