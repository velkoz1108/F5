package com.f5.service.impl;

import com.f5.model.single.Admin;
import com.f5.model.single.SystemRoles;
import com.f5.repository.single.AdminRepository;
import com.f5.repository.single.RoleRepository;
import com.f5.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : wangtao
 * @date : 2018/8/23 13:26  星期四
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin queryAdminByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }

    @Override
    public Page<Admin> findAll(Pageable pageable) {
        Page<Admin> all = adminRepository.findAll(pageable);
        return all;
    }

    @Override
    public int updateAdmin(Admin admin) {
        adminRepository.saveAndFlush(admin);
        return 1;
    }

    @Override
    public Page<SystemRoles> queryAllSystemRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
