package com.example.mpproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role_resource")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_resource_id", type = IdType.AUTO)
    private Long roleResourceId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 资源id
     */
    @TableField("resource_id")
    private Long resourceId;


}
