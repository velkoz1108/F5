package com.f5;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author : wangtao
 * @date : 2018/8/31 17:12  星期五
 */


public class NormalTest {

     ThreadLocal<String> a = new ThreadLocal<>();
    static List<String> list =null;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list = Lists.newArrayList();
                getStr();
            }).start();
        }
        Thread.sleep(1000l);
        for (String s : list) {
            System.out.println("s = " + s);
        }
    }

    public static void getStr() {
        String str = UUID.randomUUID().toString();
        list.add(str);
    }
}
