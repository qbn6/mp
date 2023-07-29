package com.example.mpproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.entity.Account;
import com.example.mpproject.entity.Customer;
import com.example.mpproject.service.AccountService;
import com.example.mpproject.service.CustomerService;
import com.example.mpproject.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;

    /**
     * 进入账号列表页
     *
     * @return
     */
    @GetMapping("/toList")
    public String toList() {
        return "account/accountList";
    }

    /**
     * 账号页面展示
     * @param realName
     * @param email
     * @param localDateTime
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String email, LocalDateTime localDateTime, Long page, Long limit) {
        LambdaQueryWrapper<Account> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(realName), Account::getRealName, realName)
                .like(StringUtils.isNotBlank(email), Account::getEmail, email)
                .like(ObjectUtils.isNotEmpty(localDateTime),Account::getCreateTime,localDateTime)
                .orderByDesc(Account::getAccountId);
        Page<Account> accountPage = accountService.page(new Page<>(page, limit), queryWrapper);
        return ResultUtil.resultPageR(accountPage);
    }

    /**
     * 进入账号新增页面
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "account/accountAdd";
    }

    /**
     * 添加账号
     * @param account
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R<Object> add(@RequestBody Account account) {
        return ResultUtil.resultInsertR(accountService.save(account));
    }




    /**
     * 进入客户修改页面
     *
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     * @param customer
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer) {
        return ResultUtil.resultInsertR(customerService.updateById(customer));
    }



    /**
     * 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable  Long id) {
        return ResultUtil.resultInsertR(customerService.removeById(id));
    }



    /**
     * 进入详情页
     *
     * @return
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        return "customer/customerDetail";
    }
}
