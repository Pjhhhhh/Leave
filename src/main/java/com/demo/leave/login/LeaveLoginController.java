package com.demo.leave.login;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;

/**
 * 
 * 用户登录验证
 * @author Pjh
 * @date 2021年2月18日
 */
@Path("/leave/login")
public class LeaveLoginController extends Controller {
    
    public void index() {
        String username = getPara("username");
        String password = getPara("password");
        Kv cond = Kv.by("username", username);
        Record a = Db.template("leave.login", cond).findFirst();
        String pwd = a.getStr("password");
        // 验证密码是否正确
        if (password.equals(pwd)) {
            String user_id = a.getStr("user_id");
            Kv cond1 = Kv.by("user_id", user_id);
            // 获取用户权限
            List<Record> perid = Db.template("leave.perid", cond1).find();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < perid.size(); i++) {
                String id = perid.get(i).getStr("per_id");
                sb.append(id);
            }
            setCookie("perid", sb.toString(), -1);
            setCookie("username", username, -1);
            setSessionAttr("user_id", user_id);
            setSessionAttr("perid", sb.toString());
            // 记录请求ip地址
            setSessionAttr("ip", getRequest().getRemoteAddr());
            setCookie("sessionId", getSession().getId(), -1, true);
            renderJson(Ret.ok("username", username));
        } else {
            renderError(403, new JsonRender(Ret.fail("message", "账号或密码错误").toJson()));
        }
    }
}
