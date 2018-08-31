package com.f5;

import com.f5.handler.MyHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : wangtao
 * @date : 2018/8/15 11:35
 */
@SpringBootApplication
@EnableJpaRepositories
public class App  {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
