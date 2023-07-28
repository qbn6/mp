package com.example.mpproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("resource")
public class Resource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("resource_id")
    private Long resourceId;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 资源名称
     */
    @TableField("resource_name")
    private String resourceName;

    /**
     * 资源类型(0、目录 1、菜单 2、按钮)
     */
    @TableField("resource_type")
    private Integer resourceType;

    /**
     * 请求地址
     */
    @TableField("url")
    private String url;

    /**
     * 权限标识码
     */
    @TableField("code")
    private String code;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;


}
