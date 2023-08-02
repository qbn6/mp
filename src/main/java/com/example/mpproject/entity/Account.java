package com.example.mpproject.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author tmac
 * @since 2023-07-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("account")
public class Account extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "account_id", type = IdType.AUTO)
    private Long accountId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 加密盐
     */
    @TableField("salt")
    private String salt;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 逻辑删除标识(0、否 1、是)
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
