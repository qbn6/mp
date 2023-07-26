package com.example.mpproject.dto;

import com.example.mpproject.entity.Account;
import lombok.Data;

@Data
public class LoginDTO {
    /**
     * 重定向或者跳转的路径
     */
    private String path;

    /**
     * 错误提示信息
     */
    private String error;

    /**
     * 当前登陆人信息
     */
    private Account account;
}
