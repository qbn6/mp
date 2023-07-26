package com.example.mpproject.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mpproject.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mpproject.vo.ResourcesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询当前登录人的角色的资源
     * @param wrapper
     * @return
     */
     List<ResourcesVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);
}
