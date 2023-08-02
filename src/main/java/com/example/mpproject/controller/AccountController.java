package com.example.mpproject.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.entity.Account;
import com.example.mpproject.entity.Customer;
import com.example.mpproject.entity.Role;
import com.example.mpproject.query.AccountQuery;
import com.example.mpproject.service.AccountService;
import com.example.mpproject.service.CustomerService;
import com.example.mpproject.service.RoleService;
import com.example.mpproject.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
    private final RoleService roleService;

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
     *
     * @param accountQuery
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(AccountQuery accountQuery) {
        IPage<Account> accountIPage = accountService.accountPage(accountQuery);
        return ResultUtil.resultPageR(accountIPage);
    }

    /**
     * 进入账号新增页面
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles",roles);
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
        setPasswordAndSalt(account);
        return ResultUtil.resultInsertR(accountService.save(account));
    }

    /**
     * 设置加密密码 和 加密盐
     * @param account
     */
    private static void setPasswordAndSalt(Account account) {
        String password = account.getPassword();
        String salt = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(salt.getBytes());
        String digestHex = md5.digestHex(password);
        account.setPassword(digestHex);
        account.setSalt(salt);
    }


    /**
     *  进入账号修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        System.out.println(id );
        Account account = accountService.getById(id);
        model.addAttribute("account",account);

        List<Role> roles = roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles",roles);

        return "account/accountUpdate";
    }

    /**
     * 修改客户
     * @param
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    public R<Object> update(@RequestBody Account account) {
        if (StringUtils.isNotBlank(account.getPassword())){
            setPasswordAndSalt(account);
        }else{
            account.setPassword(null);
        }
        setPasswordAndSalt(account);
        return ResultUtil.resultInsertR(accountService.updateById(account));
    }



    /**
     * 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable  Long id) {
        return ResultUtil.resultInsertR(accountService.removeById(id));
    }



    /**
     * 进入详情页
     *
     * @return
     */
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Account account = accountService.getById(id);
        model.addAttribute("account",account);
        return "customer/customerDetail";
    }
}
