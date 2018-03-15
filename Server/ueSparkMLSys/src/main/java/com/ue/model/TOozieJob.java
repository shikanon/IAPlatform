package com.ue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: 作者 dzc
 * @description: 类说明 工作流信息表bean
 * @Date: created in 0:15 2017/11/10
 */

@Entity
@Table(name = "ooziejob", schema = "studio")
public class TOozieJob implements java.io.Serializable {

    /**工作流id*/
    private String id;

    /**工作流名称*/
    private String name;

    /**工作流地址*/
    private String path;

    /**工作流的图信息*/
    private String graphxml;

    /**工作流的执行状态*/
    private String status;

    /**工作流提交的用户*/
    private String account;

    /**简要说明*/
    private String description;

    /**创建时间*/
    private String createTime;

    /**工作流结束时间*/
    private String endTime;

    /**是否例子。默认为‘0’*/
    private Integer isExample;

    /**对应的bdajobID*/
    private String jobId;

    /**序列数，默认为‘0’*/
    private Integer sequence;


    private   String  workflowxml;

    @Id
    @Column(name = "id", unique = true,nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    @Column(name = "graphxml")
    public String getGraphxml() {
        return graphxml;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "createtime")
    public String getCreateTime() {
        return createTime;
    }

    @Column(name = "endtime")
    public String getEndTime() {
        return endTime;
    }

    @Column(name = "isexample")
    public Integer getIsExample() {
        return isExample;
    }

    @Column(name = "jobid")
    public String getJobId() {
        return jobId;
    }

    @Column(name = "sequence")
    public Integer getSequence() {
        return sequence;
    }

    @Column(name = "workflowxml")
    public String getWorkflowxml() {
        return workflowxml;
    }

    public void setWorkflowxml(String workflowxml) {
        this.workflowxml = workflowxml;
    }

    /**无参构造函数*/
    public TOozieJob() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setGraphxml(String graphxml) {
        this.graphxml = graphxml;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setIsExample(Integer isExample) {
        this.isExample = isExample;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
