package com.example.mpproject.utils;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class APIResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int state;
    private String request;
    private String message;
    private String cause;
    private Map<String, Object> variables;
    private T data;
    private Long total;
    private String version;

    public static long getSerialVersionUID() {
        return 1L;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return this.cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Map<String, Object> getVariables() {
        return this.variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        InputStream inputStream = null;

        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("version.json");
            if (ObjectUtils.isNotEmpty(inputStream)) {
                Map map = (Map)JSONObject.parseObject(inputStream, Map.class, new Feature[0]);
                if (ObjectUtils.isNotEmpty(map)) {
                    version = map.get("version") + "";
                }
            }
        } catch (IOException var12) {
        } finally {
            try {
                if (ObjectUtils.isNotEmpty(inputStream)) {
                    inputStream.close();
                }
            } catch (IOException var11) {
            }

        }

        this.version = version;
    }

    public APIResult() {
        this.state = Constants.SUCCESS;
        this.message = "";
        this.cause = "";
        this.variables = new HashMap();
    }

    public APIResult(int state) {
        this.state = Constants.SUCCESS;
        this.message = "";
        this.cause = "";
        this.variables = new HashMap();
        this.state = state;
    }

    public APIResult(int state, String message) {
        this.state = Constants.SUCCESS;
        this.message = "";
        this.cause = "";
        this.variables = new HashMap();
        this.state = state;
        this.message = message;
    }

    public APIResult(int state, String message, String cause) {
        this.state = Constants.SUCCESS;
        this.message = "";
        this.cause = "";
        this.variables = new HashMap();
        this.state = state;
        this.message = message;
        this.cause = cause;
    }

    public boolean isSuccess() {
        return Constants.SUCCESS == this.state;
    }

    public boolean isFailed() {
        return Constants.SUCCESS != this.state;
    }

    public void addVariable(String key, Object value) {
        this.variables.put(key, value);
    }

    public Object getVariable(String key) {
        return null == this.variables ? null : this.variables.get(key);
    }

    public static APIResult ok() {
        APIResult r = new APIResult();
        r.setState(Constants.SUCCESS);
        r.setMessage("");
        r.setVersion("");
        return r;
    }

    public static APIResult error() {
        APIResult r = new APIResult();
        r.setState(Constants.FAIL);
        r.setMessage("");
        r.setVersion("");
        return r;
    }

    public APIResult state(int state) {
        this.setState(state);
        return this;
    }

    public APIResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public APIResult version(String version) {
        this.setVersion(version);
        return this;
    }

    public APIResult cause(String cause) {
        this.setCause(cause);
        return this;
    }

    public APIResult request(String request) {
        this.setRequest(request);
        return this;
    }

    public APIResult<T> data(T data) {
        this.setData(data);
        return this;
    }

    public APIResult<T> total(Long total) {
        this.setTotal(total);
        return this;
    }

    public APIResult<T> total(Integer total) {
        this.setTotal((long)total);
        return this;
    }
}
