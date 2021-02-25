package com.demo.leave.check;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;

import cn.hutool.core.util.StrUtil;

@Path("/leave/check")
@Before(CheckValidator.class)
public class LeaveCheckController extends Controller {
    
    public void index() {
        String uuid = getPara("uuid");
        String state = getPara("state");
        String opinion = getPara("opinion");
        String check = "未审核";
        if (StrUtil.isBlank(state)) {
            state = check;
        }
        if (StrUtil.isBlank(opinion)) {
            opinion = check;
        }
        Kv cond = Kv.by("state", state).set("opinion", opinion).set("uuid", uuid);
        Db.template("leave.update", cond).update();
        renderJson(Ret.ok());
    }
    
}
