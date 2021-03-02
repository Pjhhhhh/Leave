package com.demo.leave.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.json.FastJson;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * @author Pjh
 * @date 2021年3月2日
 */
@Path("/operate")
public class OperationController extends Controller {

    public void index() {
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
            String user_id = list.get(i).getStr("_id");
            String username = list.get(i).getStr("username");
            String password = list.get(i).getStr("password");
            String user_name = list.get(i).getStr("user_name");
            String post = list.get(i).getStr("post");
            String state = list.get(i).getStr("_state");
            Kv cond;
            if (state.equals("added")) {
                Record user = new Record().set("user_id", user_id).set("user_name", user_name)
                    .set("username", username).set("password", password)
                    .set("dept", dept).set("post", post);
                Db.save("leave_user", "user_id", user);
                Record role = new Record().set("id", user_id).set("role_id", post).set("user_id", user_id);
                Db.save("leave_role_user_relation", role);
            }
            if (state.equals("modified")) {
                
            }
            if (state.equals("removed")) {
                user_id = list.get(i).getStr("user_id");
                Db.deleteById("leave_role_user_relation", "id", user_id);
                Db.deleteById("leave_user", "user_id", user_id);
            }
        }
        renderJson(Ret.ok());
    }
}
