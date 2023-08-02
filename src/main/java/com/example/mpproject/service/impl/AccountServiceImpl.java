package com.example.mpproject.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.entity.Account;
import com.example.mpproject.mapper.AccountMapper;
import com.example.mpproject.query.AccountQuery;
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


    @Override
    public IPage<Account> accountPage(AccountQuery accountQuery) {
        QueryWrapper<Account> queryWrapper = Wrappers.query();
        queryWrapper.like(StringUtils.isNotBlank(accountQuery.getRealName()), "a.real_name", accountQuery.getRealName())
                .like(StringUtils.isNotBlank(accountQuery.getEmail()), "a.email", accountQuery.getEmail());
        String createTimeRange = accountQuery.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)) {
            String[] timeArray = createTimeRange.split(" - ");
            queryWrapper.ge("a.create_time", timeArray[0]).le("a.create_time", timeArray[1]);
        }
        queryWrapper.eq("a.deleted", 0).orderByDesc("a.account_id");
        return accountMapper.accountPage(new Page<>(accountQuery.getPage(), accountQuery.getLimit()), queryWrapper);


    }
}
