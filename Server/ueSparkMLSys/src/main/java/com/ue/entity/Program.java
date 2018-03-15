package com.ue.entity;

import com.ue.util.share.util.ProgramUtil;

import java.io.File;

/**
 * @auther: 作者 dzc
 * @description: 类说明 算法表实体类
 * @Date: created in 18:59 2017/11/9
 */
public class Program extends BaseEntity implements java.io.Serializable {

    /**
     * 算法对应的id
     */
    private String id;

    /**
     * 算法名称
     */
    private String name;

    /**
     * 算法对应目录
     */
    private String category;

    /**
     * 上传算法的用户邮箱
     */
    private String owner;

    /**
     * 算法在HDFS的存储位置
     */
    private String path;

    /**
     * 是否启用，默认为‘0’
     */
    private Integer deprecated;

    /**
     * 算法的类型
     */
    private String type;

    /**
     * 是否确定的算法
     */
    private Integer isDeterministic;

    /**
     * 简要说明
     */
    private String description;

    /**
     * 算法版本
     */
    private String version;

    /**
     * 算法上传时间
     */
    private String createDate;

    /**
     * 算法提交命令行
     */
    private String commandLine;

    /**
     * 不明确字段。默认为空
     */
    private String describXML;

    /**
     * 脚本的版本。默认为‘2’
     */
    private String scriptVersion;

    /**
     * 是否可编程的
     */
    private boolean programable;

    /**
     * 不明确字段。默认为空
     */
    private String sourceLink;

    /**
     * 用于上传文件所用的参数
     */
    private File upload;

    /**
     * 用于上传文件所用的参数
     */
    private String uploadFileName;

    public Integer getIsDeterministic() {
        return isDeterministic;
    }

    public void setIsDeterministic(Integer isDeterministic) {
        this.isDeterministic = isDeterministic;
    }

    public boolean isProgramable() {
        return programable;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

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

    public String getType() {
        return type;
    }

    public Integer getDeterministic() {
        return isDeterministic;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public String getDescribXML() {
        return describXML;
    }

    public String getScriptVersion() {
        return scriptVersion;
    }

    public boolean getProgramable() {
        return programable;
    }

    public String getSourceLink() {
        return sourceLink;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDeterministic(Integer deterministic) {
        isDeterministic = deterministic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public void setDescribXML(String describXML) {
        this.describXML = describXML;
    }

    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

    public void setProgramable(boolean programable) {
        this.programable = programable;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public boolean isScriptProgram() {
        return getProgramable() && !isDistributed();
    }

    public Boolean isDistributed() {
        return ProgramUtil.isDistributed(type);
    }

    public Boolean isETL() {
        return ProgramUtil.isETL(type);
    }

}
