package com.example.mpproject.service.impl;

import com.example.mpproject.entity.Resource;
import com.example.mpproject.mapper.ResourceMapper;
import com.example.mpproject.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
