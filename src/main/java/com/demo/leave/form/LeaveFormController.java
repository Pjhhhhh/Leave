package com.demo.leave.form;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.hutool.core.util.StrUtil;

@Path("/leave/form")
@Before(FormValidator.class)
public class LeaveFormController extends Controller {
    
    public void index() {
        String findName = getPara("findName");
        Kv cond1 = Kv.by("findName", findName);
        List<Record> userList;
        if (StrUtil.isNotBlank(findName)) {
            userList = Db.template("leave.form", cond1).find();
        } else {
            userList = Db.template("leave.form").find();
        }
        List<Kv> list = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            String uuid = userList.get(i).getStr("uuid");
            String date = userList.get(i).getStr("date");
            String date1 = userList.get(i).getStr("date1");
            String name = userList.get(i).getStr("name");
            String dept = userList.get(i).getStr("dept");
            String post = userList.get(i).getStr("post");
            String reason = userList.get(i).getStr("reason");
            String type = userList.get(i).getStr("type");
            String day = userList.get(i).getStr("day");
            String hour = userList.get(i).getStr("hour");
            String state = userList.get(i).getStr("state");
            String opinion = userList.get(i).getStr("opinion");
            Kv data = Kv.by("uuid", uuid).set("date", date).set("date1", date1).set("name", name).set("dept", dept)
                    .set("post", post).set("reason", reason).set("type", type)
                    .set("day", day).set("hour", hour).set("state", state).set("opinion", opinion);
            list.add(data);
        }
        renderJson(Ret.ok("data", list));
    }
    
}
