package com.ue.entity;

/**
 * @auther: 作者 dzc
 * @description: 类说明 数据表实体类
 * @Date: created in 23:13 2017/11/9
 */
public class DataSet extends BaseEntity implements java.io.Serializable {

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getOwner() {
        return owner;
    }

    public String getPath() {
        return path;
    }

    public Integer getDeprecated() {
        return deprecated;
    }

    public String getContentType() {
        return contentType;
    }

    public String getVersion() {
        return version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

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
