package com.demo.leave.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.json.FastJson;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;

import cn.hutool.crypto.SecureUtil;

/**
 * 
 * 用户管理操作
 * @author Pjh
 * @date 2021年3月2日
 */
@Path("/operate")
@Before(UserValidator.class)
public class OperationController extends Controller {

    public void index() {
        // 取得json数组
        JSONArray array = JSON.parseArray(getPara("data"));
        System.out.println(array);
        String dept = getPara("dept");
        List<Record> list = new ArrayList<>();
        for (Object object : array) {
            JSONObject jsonObj = (JSONObject) object;
            @SuppressWarnings("unchecked")
            Record record = new Record().setColumns(FastJson.getJson().parse(jsonObj.toJSONString(), Map.class));
            list.add(record);
        }
        for (int i = 0; i < list.size(); i++) {
            String user_id;
            String username = list.get(i).getStr("username");
            String name = list.get(i).getStr("name");
            String password = list.get(i).getStr("password");
            String securePassword = SecureUtil.md5(password);
            String user_name = list.get(i).getStr("user_name");
            String post = list.get(i).getStr("post");
            String state = list.get(i).getStr("_state");
            // 增加记录
            if (state.equals("added")) {
                if (checkPost(post) && checkUsername(username)) {
                    user_id = list.get(i).getStr("_id");
                    securePassword = SecureUtil.md5(password);
                    Record user = new Record().set("user_id", user_id).set("user_name", user_name).set("name", name)
                        .set("securePassword", securePassword).set("username", username).set("password", password)
                        .set("dept", dept).set("post", post);
                    Db.save("leave_user", "user_id", user);
                    Record role = new Record().set("id", user_id).set("role_id", post).set("user_id", user_id);
                    Db.save("leave_role_user_relation", role);
                    renderJson(Ret.ok());
                } else {
                    renderError(403, new JsonRender(Ret.fail("message", "不能更改").toJson()));
                }
            }
            // 修改记录
            if (state.equals("modified") ) {
                user_id = list.get(i).getStr("user_id");
                if (checkUser(user_id) && checkPost(post) && isUse(user_id, username)) {
                    user_id = list.get(i).getStr("user_id");
                    securePassword = SecureUtil.md5(password);
                    Kv cond = Kv.by("user_id", user_id).set("name", name).set("user_name", user_name)
                        .set("username", username).set("password", password).set("securePassword", securePassword).set("post", post);
                    Db.template("leave.update1", cond).update();
                    renderJson(Ret.ok());
                } else {
                    renderError(403, new JsonRender(Ret.fail("message", "不能更改").toJson()));
                }
            }
            // 删除记录
            if (state.equals("removed")) {
                user_id = list.get(i).getStr("user_id");
                if (checkUser(user_id)) {
                    Db.deleteById("leave_role_user_relation", "id", user_id);
                    Db.deleteById("leave_user", "user_id", user_id);
                    renderJson(Ret.ok());
                } else {
                    renderError(403, new JsonRender(Ret.fail("message", "不能更改").toJson()));
                }
            }
        }
        renderJson(Ret.ok());
    }
    
    /**
     * 
     * 检查是否能对记录更改
     * @param user_id 表示更改记录的user_id
     * @return 更改记录的账号的 user_id 与cookie中的账号的 user_id 相等时返回 true，否则返回 false
     */
    private boolean checkUser(String user_id) {
        String userid = getSessionAttr("user_id");
        if (user_id.equals(userid))
            return true;
        return false;
    }
    
    /**
     * 
     * 检查职位
     * @param post 表示记录中的职位
     * @return 能更改为现有职位返回 true，否则返回 false
     */
    private boolean checkPost(String post) {
        List<Record> record = Db.template("leave.role").find();
        for (int i = 0; i < record.size(); i++) {
            String role = record.get(i).getStr("role_id");
            if (post.equals(role)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * 判断是否有相同账号
     * @param username 表示登陆的账号
     * @return 如果是同一账号名则返回 false，否则返回 true
     */
    private boolean checkUsername(String username) {
        List<Record> record = Db.template("leave.username").find();
        for (int i = 0; i < record.size(); i++) {
            String list = record.get(i).getStr("username");
            if (username.equals(list)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * 检查是否更改账号
     * @param username 账号
     * @return 没有更改账号返回 true，否则返回 false
     */
    private boolean isUse(String user_id, String username) {
        Kv cond = Kv.by("user_id", user_id);
        Record record = Db.template("leave.use", cond).findFirst();
            String list = record.getStr("username");
            if (username.equals(list)) {
                return true;
            }
        return false;
    }

}
