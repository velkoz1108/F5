package com.f5.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : wangtao
 * @date : 2018/9/7 14:13  星期五
 */
@Slf4j
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "this is test controller!";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/test2")
    public String test2() {
        return "this is test controller!";
    }

    @Secured("ROLE_USER")
    @RequestMapping("/test3")
    public String test3() {
        return "this is test controller!";
    }


}
