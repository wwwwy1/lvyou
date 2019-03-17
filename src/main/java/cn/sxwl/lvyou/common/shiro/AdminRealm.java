package cn.sxwl.lvyou.common.shiro;

import cn.sxwl.lvyou.common.dao.AdminMapper;
import cn.sxwl.lvyou.common.entity.Admin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;
    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权逻辑");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        return info;
    }
    //认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
       Admin admin= adminMapper.selectByPrimaryKey(token.getUsername());
       if (admin==null){
           return null;
       }
        return new SimpleAuthenticationInfo("",admin.getApassword(),"");
    }
}
