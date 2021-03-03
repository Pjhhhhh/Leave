package com.demo.leave.user;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 用户管理功能
 * @author Pjh
 * @date 2021年3月2日
 */
@Path("/user/form")
@Before(UserValidator.class)
public class UserFormController extends Controller {

    public void index() {
        String findDept = getPara("findDept");
        Kv cond = Kv.by("findDept", findDept);
        if (StrUtil.isNotBlank(findDept)) {
            List<Record> userList = Db.template("leave.user", cond).find();
            List<Kv> list = new ArrayList<>();
            for (int i = 0; i < userList.size(); i++) {
                String user_id = userList.get(i).getStr("user_id");
                String username = userList.get(i).getStr("username");
                String password = userList.get(i).getStr("password");
                String user_name = userList.get(i).getStr("user_name");
                String dept = userList.get(i).getStr("dept");
                String post = userList.get(i).getStr("post");
                Kv data = Kv.by("user_id", user_id).set("username", username).set("password", password)
                    .set("user_name", user_name).set("dept", dept).set("post", post);
                list.add(data);
            }
            renderJson(Ret.ok("data", list));
        } else {
            renderError(403, new JsonRender(Ret.fail()));
        }
    }
}
