package com.ue.entity;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 目录表实体类
 * @Date: created in 23:09 2017/11/9
 */
public class Category extends BaseEntity implements java.io.Serializable {

    /**目录ID*/
    private String id;

    /**目录名称*/
    private String name;

    /**目录等级*/
    private String level;

    /**目录类型*/
    private String type;  //prog  data  job

    /**目录上级地址*/
    private String path;

    /**默认为0*/
    private Integer hasChild;

    /**创建时间*/
    private String createTime;

    /**目录下的算法*/
    private List<Program> programList;

    /**目录下的数据集*/
    private List<DataSet> dataSetList;

    /**目录下的工作流*/

    private  List<OozieJob> oozieJobList;

    public List<OozieJob> getOozieJobList() {
        return oozieJobList;
    }

    public void setOozieJobList(List<OozieJob> oozieJobList) {
        this.oozieJobList = oozieJobList;
    }

    public Category() {

    }

    /**存放二级目录*/
    private List<Category> childTree;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public List<Category> getChildTree() {
        return childTree;
    }

    public void setChildTree(List<Category> childTree) {
        this.childTree = childTree;
    }

    public List<DataSet> getDataSetList() {
        return dataSetList;
    }

    public void setDataSetList(List<DataSet> dataSetList) {
        this.dataSetList = dataSetList;
    }
}
