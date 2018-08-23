package com.f5.repository.single;

import com.f5.model.single.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : wangtao
 * @date : 2018/8/23 13:16  星期四
 */


public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findAdminByUsername(String username);
}
