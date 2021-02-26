package com.demo.leave.form;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.render.JsonRender;
import com.jfinal.validate.Validator;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 验证是否能获取请假记录
 * @author Pjh
 * @date 2021年2月22日
 */
public class FormValidator extends Validator {

    protected void validate(Controller c) {
        // 获取请求ip地址
        String localIp = c.getRequest().getRemoteAddr();
        String ip = c.getSessionAttr("ip");
        if (localIp.equals(ip)) {
            String id = c.getSessionAttr(("perid"));
            // 验证是否有权限
            if (StrUtil.isBlank(id)) {
                addError(id, id);
            }
        } else {
            addError(ip, ip);
        }
    }

    protected void handleError(Controller c) {
        c.renderError(403, new JsonRender(Ret.fail()));
    }
}
