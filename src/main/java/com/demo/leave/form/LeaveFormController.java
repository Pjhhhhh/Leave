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

/**
 * 
 * 显示请假记录
 * @author Pjh
 * @date 2021年2月19日
 */
@Path("/leave/form")
@Before(FormValidator.class)
public class LeaveFormController extends Controller {
    
    public void index() {
        // 查询申请人姓名
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
            String startDate = userList.get(i).getStr("startDate");
            String endDate = userList.get(i).getStr("endDate");
            String name = userList.get(i).getStr("name");
            String dept = userList.get(i).getStr("dept");
            String post = userList.get(i).getStr("post");
            String reason = userList.get(i).getStr("reason");
            String type = userList.get(i).getStr("type");
            String day = userList.get(i).getStr("day");
            String hour = userList.get(i).getStr("hour");
            String state = userList.get(i).getStr("state");
            String opinion = userList.get(i).getStr("opinion");
            Kv data = Kv.by("uuid", uuid).set("startDate", startDate).set("endDate", endDate).set("name", name).set("dept", dept)
                    .set("post", post).set("reason", reason).set("type", type)
                    .set("day", day).set("hour", hour).set("state", state).set("opinion", opinion);
            list.add(data);
        }
        renderJson(Ret.ok("data", list));
    }
}
