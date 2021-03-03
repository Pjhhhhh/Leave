package com.demo.leave.leave;

import java.util.Arrays;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.render.JsonRender;
import com.jfinal.validate.Validator;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 验证是否有权限申请请假
 * @author Pjh
 * @date 2021年2月22日
 */
public class LeaveValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        // 获取请求ip地址
        String localIp = c.getRequest().getRemoteAddr();
        // 获取session中的ip地址
        String ip = c.getSessionAttr("ip");
        // 验证是否是同一个ip地址的请求
        if (localIp.equals(ip)) {
            String id = c.getSessionAttr(("perid"));
            // 验证是否有权限
            if (StrUtil.isBlank(id)) {
                addError(id, "验证失败");
            } else {
                String[] arr = id.split("");
                // 验证是否有申请权限
                if (!Arrays.asList(arr).contains("1")) {
                    addError(id, id);
                }
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
