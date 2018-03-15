package com.ue.entity;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 dzc
 * @description: 类说明 返回结果状态
 * @Date: created in 17:23 2017/11/8
 */
public class ResultJson extends BaseEntity implements java.io.Serializable {

    /** 返回操作结果状态*/
    private Boolean status;

    /** 返回操作结果描述*/
    private String message;

    /** 返回操作结果list*/
    private List<T> list;

    /** 返回操作结果Map*/
    private Map<String,Object> map;

    /** 返回操作结果ListMap*/
    private Map<String,List<T>> listMap;

    /** 返回操作结果Object*/
    private Object object;

    public ResultJson() {

    }
    public ResultJson(Boolean status, String message, List<T> list) {
        this.status = status;
        this.message = message;
        this.list = list;
    }

    public ResultJson(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultJson(Boolean status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public ResultJson(Boolean status, String message, Map<String, List<T>> listMap) {
        this.status = status;
        this.message = message;
        this.listMap = listMap;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getList() {
        return list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, List<T>> getListMap() {
        return listMap;
    }

    public void setListMap(Map<String, List<T>> listMap) {
        this.listMap = listMap;
    }
}
