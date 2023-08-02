package com.example.mpproject.query;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountQuery {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 时间
     */
    private String createTimeRange;
    private Long page;
    private Long limit;
}
