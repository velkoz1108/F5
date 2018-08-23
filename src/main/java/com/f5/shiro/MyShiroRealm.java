package com.f5.shiro;

import com.f5.model.single.Admin;
import com.f5.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @author : wangtao
 * @date : 2018/8/16 16:19  星期四
 */


public class MyShiroRealm extends AuthorizingRealm {
    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户名
        String name = (String) principals.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //添加角色
        simpleAuthorizationInfo.addRole("admin");

        //添加权限
        simpleAuthorizationInfo.addStringPermission("create");

        return simpleAuthorizationInfo;
    }

    @Autowired
    private AdminService adminService;

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String name = usernamePasswordToken.getUsername();
        Admin admin = adminService.queryAdminByUsername(name);
        if (ObjectUtils.isEmpty(admin)) {
            throw new UnknownAccountException("用户不存在");
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, "123456", getName());

        //以账号作为 盐值
        ByteSource salt = ByteSource.Util.bytes(name);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), salt, getName());
        return simpleAuthenticationInfo;
    }
}
