package com.example.mpproject.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.mpproject.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

/**
 * 自动填充类
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName("createTime", metaObject);
        if (ObjectUtils.isNull(createTime)) {
            boolean hasSetter = metaObject.hasSetter("createTime");
            if (hasSetter) {
                setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            }
        }
        boolean hasSetter = metaObject.hasSetter("createAccountId");
        if (hasSetter) {
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (ObjectUtils.isNotEmpty(account)) {
                Long accountId = ((Account) account).getAccountId();
                setFieldValByName("createAccountId", accountId, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("modifiedTime", metaObject);
        if (ObjectUtils.isNull(updateTime)) {
            boolean hasSetter = metaObject.hasSetter("modifiedTime");
            if (hasSetter) {
                setFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
            }
        }

        boolean hasSetter = metaObject.hasSetter("modifiedAccountId");
        if (hasSetter) {
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (ObjectUtils.isNotEmpty(account)) {
                Long accountId = ((Account) account).getAccountId();
                setFieldValByName("modifiedAccountId", accountId, metaObject);
            }
        }


    }


}
