package com.example.mpproject.service;

import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
public interface AccountService extends IService<Account> {

    LoginDTO login(String name, String password);
}
