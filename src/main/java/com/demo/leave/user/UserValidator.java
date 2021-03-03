package com.demo.leave.user;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.render.JsonRender;
import com.jfinal.validate.Validator;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 验证能否对用户进行管理
 * @author Pjh
 * @date 2021年3月3日
 */
public class UserValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        String localIp = c.getRequest().getRemoteAddr();
        String ip = c.getSessionAttr("ip");
        if (localIp.equals(ip)) {
            // 验证是否有用户登录
            String user_id = c.getSessionAttr(("user_id"));
            if (StrUtil.isBlank(user_id)) {
                addError(user_id, user_id);
            }
        } else {
            addError(ip, ip);
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderError(403, new JsonRender(Ret.fail("message", "没有权限").toJson()));
    }
}
