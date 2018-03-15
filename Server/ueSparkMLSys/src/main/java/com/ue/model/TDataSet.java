package com.ue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: 作者 dzc
 * @description: 类说明 数据表实体类bean
 * @Date: created in 0:11 2017/11/10
 */

@Entity
@Table(name = "dataset", schema = "studio")
public class TDataSet implements java.io.Serializable {

    /**数据id*/
    private String id;

    /**数据名称*/
    private String name;

    /**数据所属目录*/
    private String category;

    /**数据上传者*/
    private String owner;

    /**数据存储地址*/
    private String path;

    /**是否启用*/
    private Integer deprecated;

    /**不使用，默认为空*/
    private String contentType;

    /**数据版本*/
    private String version;

    /**数据上传时间*/
    private String createDate;

    /**数据说明*/
    private String description;

    /**数据类型 可选：csv、tsv、json、general*/
    private String storeType;

    /**无参构造函数*/
    public TDataSet() {

    }
    public TDataSet(String id,String name,String category){
        this.id=id;
        this.name=name;
        this.category=category;
    }
    @Id
    @Column(name = "id", unique = true,nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    @Column(name = "path", nullable = false)
    public String getPath() {
        return path;
    }

    @Column(name = "deprecated")
    public Integer getDeprecated() {
        return deprecated;
    }

    @Column(name = "contenttype")
    public String getContentType() {
        return contentType;
    }

    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    @Column(name = "createdate")
    public String getCreateDate() {
        return createDate;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "storetype")
    public String getStoreType() {
        return storeType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDeprecated(Integer deprecated) {
        this.deprecated = deprecated;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }
}
