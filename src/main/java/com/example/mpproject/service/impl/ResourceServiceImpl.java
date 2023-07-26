package com.example.mpproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mpproject.entity.Resource;
import com.example.mpproject.mapper.ResourceMapper;
import com.example.mpproject.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mpproject.vo.ResourcesVO;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final ResourceMapper resourceMapper;

    @Override
    public List<ResourcesVO> listResourceByRoleId(Long roleId) {
        /**
         * 联表的构造器
         */
        QueryWrapper<Resource> query = Wrappers.<Resource>query();
        //查出父级菜单
        query.eq("rr.role_id", roleId).isNull("re.parent_id");
        List<ResourcesVO> resourcesVOS = resourceMapper.listResource(query);
        //获取子级菜单
        resourcesVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            //查出每一个有父id的菜单项
            QueryWrapper<Resource> subWrapper = Wrappers.<Resource>query();
            subWrapper.eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId);
            List<ResourcesVO> subResourceVOS = resourceMapper.listResource(subWrapper);
            //如果有子菜单项  填入获取的VO中
            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });

        return resourcesVOS;
    }
}
