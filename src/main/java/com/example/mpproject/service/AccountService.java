package com.example.mpproject.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.dto.LoginDTO;
import com.example.mpproject.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mpproject.query.AccountQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
public interface AccountService extends IService<Account> {
    /**
     * 登录信息查询
     * @param name
     * @param password
     * @return
     */
    LoginDTO login(String name, String password);


    /**
     * 分页查询账号
     * @param accountQuery
     * @return
     */
    IPage<Account> accountPage(AccountQuery accountQuery);
}
