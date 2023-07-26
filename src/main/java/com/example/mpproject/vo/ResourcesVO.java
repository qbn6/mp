package com.example.mpproject.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class ResourcesVO {

    private Long resourceId;

    private String resourceName;


    private String url;

    /**
     * 下级资源
     */
    private List<ResourcesVO> subs;
}
