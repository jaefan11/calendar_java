package com.scu.szmt.shrio;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShrioConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap=new LinkedHashMap<String, String>();

        /**
         * 常见过滤器：
         * anon:无需认证
         * authc:必须认证
         * user:如果使用rememberMe可以访问
         * perms:必须得到资源权限才可以访问
         * role:得到角色权限才能访问
         */

        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/

        filterMap.put("/login","anon");
        filterMap.put("/*","authc");


        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return  shiroFilterFactoryBean;

    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
