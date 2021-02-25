package com.demo.leave.start;

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

public class LeaveConfig extends JFinalConfig {
    
    private static Prop p;
    
    static void loadConfig() {
        if (p == null) {
            p = PropKit.useFirstFound("leave-config-dev.txt");
        }
    }

    public void configConstant(Constants me) {
       me.setDevMode(true);
    }
    
    public void configRoute(Routes me) {
       me.scan("com.demo.leave");
    }
    
    public static DruidPlugin getDruidPlugin() {
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
    }
    
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
    
    public void configEngine(Engine me) {
        
    }
    
    public void configInterceptor(Interceptors me) {
        
    }
    
    public void configHandler(Handlers me) {
        
    }
    
}
