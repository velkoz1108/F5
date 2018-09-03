package com.f5.handler;

import com.alibaba.fastjson.JSON;
import com.f5.model.single.ImageIcon;
import com.f5.model.single.Menu;
import com.f5.service.ImageService;
import com.f5.service.MenuService;
import com.f5.utils.MenuTreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;

/**
 * @author : wangtao
 * @date : 2018/8/31 17:38  星期五
 */

@Slf4j
public class MyHandler implements HandlerInterceptor {
    @Autowired
    private ImageService imageService;
    @Autowired
    private MenuService menuService;

    String[] icons = {"/favicon/Health.ico", "/favicon/Time.ico", "/favicon/Music.ico", "/favicon/Photo.ico", "/favicon/Task.ico", "/favicon/Five.ico", "/favicon/Game.ico"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        int i = Calendar.getInstance().get(DAY_OF_WEEK);
        List<ImageIcon> all = imageService.findAll();
        if (!CollectionUtils.isEmpty(all) && all.size() >= i) {
            request.setAttribute("todayLogo", all.get(i - 1).getFilePath());
        } else {
            request.setAttribute("todayLogo", "/favicon/Five.ico");
        }
        log.debug("[MyHandler.preHandle] logo is :{}", request.getAttribute("todayLogo"));

        HttpSession session = WebUtils.toHttp(request).getSession(false);
        if (!ObjectUtils.isEmpty(session)) {
            List<Menu> allMenu = menuService.findAllMenu();
            List<Menu> menus = MenuTreeUtil.buildTree(allMenu);
            log.info(JSON.toJSONString(menus));
            session.setAttribute("menuList", menus);
        } else {
            log.debug("session is null");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("[MyHandler.afterCompletion]");
    }
}
