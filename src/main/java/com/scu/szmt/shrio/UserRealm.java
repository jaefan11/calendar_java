package com.scu.szmt.shrio;

import com.scu.szmt.dao.User;
import com.scu.szmt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
//        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
//        Subject subject= SecurityUtils.getSubject();
//        User user=(User)subject.getPrincipal();
//        User dbUser=userService.findById(user.getId());
//        info.addStringPermission(dbUser.getPerms());
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证");
       /* String name="admin";
        String password="admin";*/

        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername());
        if(user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
