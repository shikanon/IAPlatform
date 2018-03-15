package com.ue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: 作者 dzc
 * @description: 类说明 工作流实体类bean
 * @Date: created in 0:04 2017/11/10
 */

@Entity
@Table(name = "bdajob", schema = "studio")
public class TBdaJob implements java.io.Serializable {
    /**工作流id*/
    private String jobId;

    /**工作流名称*/
    private String jobName;

    /**图节点信息*/
    private String graphxml;

    /**用户名*/
    private String account;

    /**说明*/
    private String description;

    /**最后提交时间*/
    private String lastSubmitTime;

    /**结束时间*/
    private String endTime;

    /**是否例子*/
    private Integer isExample;

    /**oozie工作流ID*/
    private String oozieJob;



    private  String workflowxml;


    /** useease 前端所需要的图信息  */
    private  String graphmodel;

    /**无参构造函数*/
    public TBdaJob() {

    }

    @Id
    @Column(name = "job_id", unique = true,nullable = false)
    public String getJobId() {
        return jobId;
    }

    @Column(name = "graphmodel")
    public String getGraphmodel() {
        return graphmodel;
    }

    public void setGraphmodel(String graphmodel) {
        this.graphmodel = graphmodel;
    }

    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    @Column(name = "graphxml")
    public String getGraphxml() {
        return graphxml;
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "last_submit_time")
    public String getLastSubmitTime() {
        return lastSubmitTime;
    }

    @Column(name = "endtime")
    public String getEndTime() {
        return endTime;
    }

    @Column(name = "is_example")
    public Integer getIsExample() {
        return isExample;
    }

    @Column(name = "oozie_job")
    public String getOozieJob() {
        return oozieJob;
    }

    @Column(name = "workflowxml")
    public String getWorkflowxml() {
        return workflowxml;
    }

    public void setWorkflowxml(String workflowxml) {
        this.workflowxml = workflowxml;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setGraphxml(String graphxml) {
        this.graphxml = graphxml;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastSubmitTime(String lastSubmitTime) {
        this.lastSubmitTime = lastSubmitTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setIsExample(Integer isExample) {
        this.isExample = isExample;
    }

    public void setOozieJob(String oozieJob) {
        this.oozieJob = oozieJob;
    }
}

