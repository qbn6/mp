package com.example.mpproject.service.impl;

import com.example.mpproject.entity.Customer;
import com.example.mpproject.mapper.CustomerMapper;
import com.example.mpproject.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
