package com.example.mpproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mpproject.entity.Customer;
import com.example.mpproject.service.CustomerService;
import com.example.mpproject.utils.APIResult;
import com.example.mpproject.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /**
     * 进入列表页
     * @return
     */
    @GetMapping("/toList")
    public String toList(){
        return "customer/customerList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String,Object>> list(String realName, String phone, Long page, Long limit){
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(realName),Customer::getRealName,realName)
                .like(StringUtils.isNotBlank(phone),Customer::getPhone,phone)
                .orderByDesc(Customer::getCustomerId);
        Page<Customer> customerPage = customerService.page(new Page<>(page, limit), queryWrapper);
        System.out.println(123);
        return ResultUtil.resultPageR(customerPage);

    }
}
