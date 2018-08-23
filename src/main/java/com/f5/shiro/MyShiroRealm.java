package com.f5.shiro;

import com.alibaba.fastjson.JSON;
import com.f5.model.single.*;
import com.f5.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/8/16 16:19  星期四
 */

@Slf4j
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

        Admin admin = adminService.queryAdminByUsername(name);
        Set<AdminRoles> rolesList = admin.getAdminRoles();
        List<String> roles = new ArrayList<>();
        for (AdminRoles role : rolesList) {
            roles.add(role.getSystemRoles().getRoleName());
        }
        log.info("roles is :{}", JSON.toJSONString(roles));
        //添加角色
        simpleAuthorizationInfo.addRoles(roles);
        Set<AdminPermission> permissionList = admin.getAdminPermissions();
        List<String> permissions = new ArrayList<>();
        for (AdminPermission permission : permissionList) {
            permissions.add(permission.getSystemPermission().getPermissionName());
        }
        log.info("permissions is :{}", JSON.toJSONString(permissions));
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permissions);

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
