package com.f5.shiro;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.AnonymousFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author : wangtao
 * @date : 2018/8/31 11:30  星期五
 */

@Slf4j
public class MyAnonymousFilter extends AnonymousFilter {
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.debug("[MyAnonymousFilter] mappedValue is :{}", JSON.toJSONString(mappedValue));
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
    }
}
