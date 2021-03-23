package com.demo.leave.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * 
 * 配置JFinal运行参数
 * @author Pjh
 * @date 2021年2月18日
 */
public class LeaveConfig extends JFinalConfig {
    
    private static Prop p;
    
    /**
     * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
     * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
     */
    static void loadConfig() {
        if (p == null) {
            p = PropKit.useFirstFound("Leave-config-dev.txt");
        }
    }

    public void configConstant(Constants me) {
       me.setDevMode(true);
    }
    
    public void configRoute(Routes me) {
       me.scan("com.demo.leave");
    }
    
    /**
     * 抽取成独立的方法，便于 _Generator 中重用该方法，减少代码冗余
     */
    public static DruidPlugin getDruidPlugin() {
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
    }
    
    /**
     * 配置数据库
     */
    public void configPlugin(Plugins me) {
        DruidPlugin dp = getDruidPlugin();
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin("pgsql", dp);
        arp.setDevMode(true);
        arp.setShowSql(true);
        arp.addSqlTemplate("leave/jdbc.txt");
        me.add(arp);
        arp.setDialect(new PostgreSqlDialect());
    }
    
    public void configEngine(Engine me) {}
    
    public void configInterceptor(Interceptors me) {}
    
    public void configHandler(Handlers me) {}
}
