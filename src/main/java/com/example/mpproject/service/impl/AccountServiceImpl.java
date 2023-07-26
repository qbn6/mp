package com.example.mpproject.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.entity.Account;
import com.example.mpproject.mapper.AccountMapper;
import com.example.mpproject.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    private final AccountMapper accountMapper;

    @Override
    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPath("redirect:/");
        Account account = lambdaQuery().eq(StringUtils.isNotBlank(username), Account::getUsername, username).one();
        if (ObjectUtils.isEmpty(account)) {
            loginDTO.setError("用户名不存在");
            return loginDTO;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if (!digestHex.equals(account.getPassword())) {
            loginDTO.setError("密码错误");
            return loginDTO;
        }
        loginDTO.setAccount(account);
        loginDTO.setPath("login/main");
        return loginDTO;
    }
}
