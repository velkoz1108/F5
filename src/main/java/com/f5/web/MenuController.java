package com.f5.web;

import com.alibaba.fastjson.JSON;
import com.f5.model.single.Menu;
import com.f5.service.MenuService;
import com.f5.utils.MenuTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : wangtao
 * @date : 2018/9/4 10:13  星期二
 */

@Slf4j
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequiresUser
    @RequestMapping(value = "/menu/list")
    public String menuList(Model model) {
        List<Menu> allMenu = menuService.findAllMenu();
        model.addAttribute("allMenu", allMenu);
        return "menu-list";
    }
}
