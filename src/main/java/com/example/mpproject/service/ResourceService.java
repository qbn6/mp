package com.example.mpproject.service;

import com.example.mpproject.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mpproject.vo.ResourcesVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 根据角色id查询资源
     * @param roleId
     * @return
     */
    List<ResourcesVO>  listResourceByRoleId(Long roleId);
}
