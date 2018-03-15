package com.ue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: 作者 dzc
 * @description: 类说明 算法表实体类bean
 * @Date: created in 0:17 2017/11/10
 */

@Entity
@Table(name = "program", schema = "studio")
public class TProgram implements java.io.Serializable {

    /**算法对应的id*/
    private String id;

    /**算法名称*/
    private String name;

    /**算法对应目录*/
    private String category;

    /**上传算法的用户邮箱*/
    private String owner;

    /**算法在HDFS的存储位置*/
    private String path;

    /**是否启用，默认为‘0’*/
    private Integer deprecated;

    /**算法的类型*/
    private String type;

    /**是否确定的算法*/
    private Integer isDeterministic;

    /**简要说明*/
    private String description;

    /**算法版本*/
    private String version;

    /**算法上传时间*/
    private String createDate;

    /**算法提交命令行*/
    private String commandLine;

    /**不明确字段。默认为空*/
    private String describXML;

    /**脚本的版本。默认为‘2’*/
    private String scriptVersion;

    /**是否可编程的*/
    private Integer programable;

    /**不明确字段。默认为空*/
    private String sourceLink;


    /**无参构造函数*/
    public TProgram() {

    }

    public TProgram(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    @Id
    @Column(name = "id", unique = true,nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name")
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

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    @Column(name = "deprecated")
    public Integer getDeprecated() {
        return deprecated;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Column(name = "isdeterministic")
    public Integer getIsDeterministic() {
        return isDeterministic;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    @Column(name = "createdate")
    public String getCreateDate() {
        return createDate;
    }

    @Column(name = "commandline")
    public String getCommandLine() {
        return commandLine;
    }

    @Column(name = "describXML")
    public String getDescribXML() {
        return describXML;
    }

    @Column(name = "scriptversion")
    public String getScriptVersion() {
        return scriptVersion;
    }

    @Column(name = "programable")
    public Integer getProgramable() {
        return programable;
    }

    @Column(name = "sourcelink")
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

    public void setIsDeterministic(Integer isDeterministic) {
        this.isDeterministic = isDeterministic;
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

    public void setProgramable(Integer programable) {
        this.programable = programable;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
}
