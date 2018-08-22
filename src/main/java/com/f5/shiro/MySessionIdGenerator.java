package com.f5.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Random;

/**
 * @author : wangtao
 * @date : 2018/8/22 10:32  星期三
 */

@Component
public class MySessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return Long.toString(System.currentTimeMillis()) + (long) (Math.random() * 10000);
    }
}
