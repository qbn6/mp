package com.example.mpproject.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {

    /**
     * 构建分页查询的返回结果
     * @param page
     * @return
     */
    public static R<Map<String,Object>> resultPageR(IPage<?> page){
        Map<String,Object> data=new HashMap<>();
        data.put("count",page.getTotal());
        data.put("records",page.getRecords());
        return R.ok(data);
    }

    /**
     * 构造新增通用插入结果集
     * @param success
     * @return
     */
    public static  R<Object> resultInsertR(boolean success){
        if (success) {
            return R.ok(null);
        }
        return R.failed("新增失败");
    }
}
