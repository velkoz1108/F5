package com.f5.service;

import com.f5.model.single.Admin;
import org.springframework.stereotype.Service;

/**
 * @author : wangtao
 * @date : 2018/8/23 13:25  星期四
 */


public interface AdminService {
    Admin queryAdminByUsername(String username);
}
