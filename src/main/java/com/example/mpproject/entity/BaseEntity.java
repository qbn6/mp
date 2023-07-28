package com.example.mpproject.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author 
 * @Date 2023/7/26 10:06
 * @Description 
 * @Since version-1.0
 */

@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "modified_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedTime;


    /**
     * 创建人
     */
    @TableField(value = "create_account_id", fill = FieldFill.INSERT)
    private Long createAccountId;

    /**
     * 修改人
     */
    @TableField(value = "modified_account_id", fill = FieldFill.UPDATE)
    private Long modifiedAccountId;
}
