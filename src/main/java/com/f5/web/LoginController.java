package com.f5.web;

import com.alibaba.fastjson.JSON;
import com.f5.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : wangtao
 * @date : 2018/8/16 16:31  星期四
 */
@Slf4j
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
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
    @PostMapping("/login")
    public String doLogin(LoginDto loginDto) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassword());
        token.setRememberMe(loginDto.isRememberMe());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
        } catch (Exception e) {
            return "login";
        }
        if (subject.isAuthenticated()) {
            Session session = subject.getSession(false);
            log.debug("[login] session is :{}", JSON.toJSONString(session));
            session.setAttribute("username", loginDto.getUsername());
        }

        return "redirect:index";
    }

    @RequiresUser
    @RequestMapping(value = "/")
    public String home(Model model) {
        return "redirect:index";
    }

    @RequiresUser
    @RequestMapping(value = "/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        model.addAttribute("username", principal);
        return "index";
    }


    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    //错误页面展示
//    @GetMapping("/error")
//    public String error() {
//        return "error ok!";
//    }

    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create() {
        return "Create success!";
    }

    @RequiresRoles(value = {"detail", "admin"}, logical = Logical.OR)
    @RequiresPermissions("create")
    @RequestMapping(value = "/create2")
    @ResponseBody
    public String detail2() {
        return "uid";
    }

    @RequiresRoles(value = {"detail", "admin"}, logical = Logical.OR)
    @RequiresPermissions("detail")
    @RequestMapping(value = "/detail")
    @ResponseBody
    public String detail() {
        return "uid";
    }

    @RequiresRoles(value = {"delete", "admin"}, logical = Logical.AND)
    @RequiresPermissions("delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete() {
        return "uid";
    }
}
