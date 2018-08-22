package com.f5.shiro;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;

/**
 * @author : wangtao
 * @date : 2018/8/22 9:32  星期三
 */

@Slf4j
public class MySessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MySessionIdGenerator mySessionIdGenerator;

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
//        Serializable sessionId = super.doCreate(session);
        //重写session id生成规则
        Serializable sessionId = mySessionIdGenerator.generateId(session);
        assignSessionId(session, sessionId);

        log.debug("[doCreate] sessionId is :{}", sessionId.toString());
        //存入redis
        redisTemplate.opsForValue().set(sessionId, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(sessionId);
        }
        log.debug("[doReadSession] session is :{}", JSON.toJSONString(session));
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisTemplate.opsForValue().set(session.getId(), session);
        log.debug("[doUpdate] session is :{}", JSON.toJSONString(session));
    }

    @Override
    protected void doDelete(Session session) {
        log.debug("[doDelete] session is :{}", JSON.toJSONString(session));
        super.doDelete(session);
        redisTemplate.delete(session.getId());
    }
}
