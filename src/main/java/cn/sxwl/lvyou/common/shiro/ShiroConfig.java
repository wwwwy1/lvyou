package cn.sxwl.lvyou.common.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
@Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //shiro内置过滤器  anon:无需认证 authc:必须认证才能访问 user:如果使用rememberMe可以直接访问
        // perms:该资源必须得到资源权限才能访问 role:该资源必须得到角色权限才能访问
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/admin/*","authc");
        filterMap.put("/user/*","anon");
        //filterMap.put("/users","perms[user:add]");
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

@Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(adminRealm);
        return defaultWebSecurityManager;
    }
    @Bean(name = "adminRealm")
    public AdminRealm getRealm(){
        return  new AdminRealm();
    }
}
