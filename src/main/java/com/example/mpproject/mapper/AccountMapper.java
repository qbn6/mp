package com.example.mpproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpproject.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 分页查询账号
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page,@Param(Constants.WRAPPER) Wrapper<Account> wrapper);
}
