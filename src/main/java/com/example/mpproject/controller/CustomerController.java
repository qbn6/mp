package com.example.mpproject.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     *
     * @return
     */
    @GetMapping("/toList")
    public String toList() {
        return "customer/customerList";
    }

    /**
     * 客户页面展示
     *
     * @param realName
     * @param phone
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(StringUtils.isNotBlank(realName), Customer::getRealName, realName).like(StringUtils.isNotBlank(phone), Customer::getPhone, phone).orderByDesc(Customer::getCustomerId);

        Page<Customer> customerPage = customerService.page(new Page<>(page, limit), queryWrapper);
        return ResultUtil.resultPageR(customerPage);
    }

    /**
     * 进入客户新增页面
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {
        return ResultUtil.resultInsertR(customerService.save(customer));
    }

    /**
     * 进入客户修改页面
     *
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        System.out.println(""+id+"=====================");
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     *
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
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
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
        model.addAttribute("customer", customer);
        return "customer/customerDetail";
    }

}
