package com.example.mpproject;

import com.example.mpproject.entity.Customer;
import com.example.mpproject.mapper.CustomerMapper;
import com.example.mpproject.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class CustomerTest {
    
    @Resource
    private CustomerService customerService;
    @Test
    public void test(){

    }
}
