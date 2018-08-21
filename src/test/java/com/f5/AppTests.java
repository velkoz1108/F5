package com.f5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Twin
 * @Team Atplan
 * @date : 2018/8/15 11:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTests {
    @Test
    public void contextLoads() {
        System.out.println("true = " + true);
    }

    @Test
    public void testMd5() {
        String username = "wangtao";
        String password = "www123";
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials = password;//密码原值

        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值

        int hashIterations = 1024;//加密1024次

        SimpleHash hash = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        System.out.println("hash = " + hash.toString());
    }
}
