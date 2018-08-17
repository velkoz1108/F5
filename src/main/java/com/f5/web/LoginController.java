package com.f5.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wangtao
 * @date : 2018/8/16 16:31  星期四
 */

@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "need login";
    }

    /**
     * 使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
     *
     * @return
     */
    @RequiresAuthentication
    @GetMapping("/search")
    public String search() {
        return "search";
    }

    /**
     * 当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法。
     *
     * @return
     */
    @RequiresUser
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    /**
     * 使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录
     *
     * @return
     */
    @RequiresGuest
    @GetMapping("/guest")
    public String guest() {
        return "i am a guest";
    }


    //登录
    @GetMapping("/doLogin")
    public String doLogin(String uid, String pwd) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uid, pwd);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
        } catch (Exception e) {
            return "login failed";
        }

        return "login success";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    //错误页面展示
    @GetMapping("/error")
    public String error() {
        return "error ok!";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create() {
        return "Create success!";
    }

    @RequiresRoles(value = {"detail", "admin"}, logical = Logical.OR)
    @RequiresPermissions("create")
    @RequestMapping(value = "/create2")
    public String detail2() {
        return "uid";
    }

    @RequiresRoles(value = {"detail", "admin"}, logical = Logical.OR)
    @RequiresPermissions("detail")
    @RequestMapping(value = "/detail")
    public String detail() {
        return "uid";
    }

    @RequiresRoles(value = {"delete", "admin"}, logical = Logical.AND)
    @RequiresPermissions("delete")
    @RequestMapping(value = "/delete")
    public String delete() {
        return "uid";
    }
}
