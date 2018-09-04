package com.f5.repository.single;

import com.f5.model.single.SystemRoles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : wangtao
 * @date : 2018/9/4 14:01  星期二
 */


public interface RoleRepository extends JpaRepository<SystemRoles, Long> {
}
