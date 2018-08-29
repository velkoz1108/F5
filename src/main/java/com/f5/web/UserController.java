package com.f5.web;

import com.alibaba.fastjson.JSON;
import com.f5.model.single.Admin;
import com.f5.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : wangtao
 * @date : 2018/8/29 16:46  星期三
 */

@Slf4j
@Controller
public class UserController {
    @Autowired
    private AdminService adminService;


    @RequiresUser
    @RequestMapping(value = "/user/list")
    public String userList(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        model.addAttribute("username", principal);


        Pageable pageable = PageRequest.of(0, 5);
        Page<Admin> adminList = adminService.findAll(pageable);
        log.info(JSON.toJSONString(adminList));
        model.addAttribute("userList", adminList.getContent());
        return "user-list";
    }
}
