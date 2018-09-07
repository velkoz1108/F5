package com.f5.security;

import com.alibaba.fastjson.JSON;
import com.f5.mapper.base.AdminMapper;
import com.f5.mapper.base.RolesMapper;
import com.f5.model.base.Admin;
import com.f5.model.base.AdminExample;
import com.f5.model.base.Roles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author : wangtao
 * @date : 2018/9/7 16:36  星期五
 */

@Slf4j
public class SecurityUserService implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(s);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins == null || admins.size() != 1) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //查询角色
        List<Roles> roles = rolesMapper.selectByExample(null);
        Admin admin = admins.get(0);
        SecurityUser user = new SecurityUser();
        user.setId(admin.getId());
        user.setUsername(admin.getUsername());
        user.setPassword(admin.getPassword());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setRoles(roles);
        log.debug("UserDetails is : {}", JSON.toJSONString(user));
        return user;
    }
}
