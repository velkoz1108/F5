package com.f5.shiro;

import com.f5.model.single.Admin;
import com.f5.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;
import java.util.Locale;

/**
 * @author : wangtao
 * @date : 2018/8/30 14:45  星期四
 */

@Slf4j
public class MyLogoutHandler extends LogoutFilter {
//    @Autowired
//    private AdminService adminService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        log.debug("[MyLogoutHandler] {} 退出了... ", subject.getPrincipal());
//        try {
//            if (!ObjectUtils.isEmpty(subject.getPrincipal()) && !StringUtils.isEmptyOrWhitespace(subject.getPrincipal().toString())) {
//                Admin admin = adminService.queryAdminByUsername(subject.getPrincipal().toString());
//                if (!ObjectUtils.isEmpty(admin)) {
//                    admin.setLastAccessTime(new Date());
//                    adminService.updateAdmin(admin);
//                }
//
//            }
//        } catch (Exception e) {
//            log.warn("记录用户退出信息异常", e);
//        }
        // Check if POST only logout is enabled
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }
        String redirectUrl = getRedirectUrl(request, response, subject);
        //try/catch added for SHIRO-298:
        try {
            subject.logout();
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        issueRedirect(request, response, redirectUrl);
        return false;
    }

    @Override
    public void setRedirectUrl(String redirectUrl) {
        log.debug("[MyLogoutHandler] setRedirectUrl : {}", redirectUrl);
        super.setRedirectUrl(redirectUrl);
    }
}
