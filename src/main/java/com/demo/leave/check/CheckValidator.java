package com.demo.leave.check;

import java.util.Arrays;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.render.JsonRender;
import com.jfinal.validate.Validator;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 验证是否有审核权限
 * @author Pjh
 * @date 2021年2月22日
 */
public class CheckValidator extends Validator {

    protected void validate(Controller c) {
        // 获取请求ip地址
        String localIp = c.getRequest().getRemoteAddr();
        String ip = c.getSessionAttr("ip");
        if (localIp.equals(ip)) {
            String id = c.getSessionAttr(("perid"));
            if (StrUtil.isBlank(id)) {
                addError(id, id);
            } else {
                String[] arr = id.split("");
                // 验证是否有审核权限id
                if (!Arrays.asList(arr).contains("2")) {
                    addError(id, id);
                }
            }
        } else {
            addError(ip, ip);
        }
    }

    protected void handleError(Controller c) {
        c.renderError(403, new JsonRender(Ret.fail()));
    }
}
