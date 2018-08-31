package com.f5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Twin
 * @Team Atplan
 * @date : 2018/8/15 11:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "dev")
public class AppTests {
    @Test
    public void contextLoads() {
        System.out.println("true = " + true);
    }

    @Autowired
    private Environment environment;

    @Before
    public void before() {
        String[] activeProfiles = environment.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            System.out.println("activeProfile = " + activeProfile);
        }
    }


    @Test
    public void testMd5() {
        String username = "admin";
        String password = "123456";
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials = password;//密码原值

        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值

        int hashIterations = 1024;//加密1024次

        SimpleHash hash = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        System.out.println("hash = " + hash.toString());
    }
}
