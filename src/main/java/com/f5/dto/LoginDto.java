package com.f5.dto;

import lombok.Data;

/**
 * @author : wangtao
 * @date : 2018/8/21 15:08  星期二
 */

@Data
public class LoginDto {
    private String username;
    private String password;
    private boolean isRememberMe;
}
