package com.ue.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 工作流信息表
 * @Date: created in 23:27 2017/11/9
 */
public class OozieJob extends BaseEntity implements java.io.Serializable {

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
    private Date createTime;

    /**工作流结束时间*/
    private Date endTime;

    /**是否例子。默认为‘0’*/
    private Integer isExample;

    /**对应的bdajobID*/
    private String jobId;

    /**序列数，默认为‘0’*/
    private Integer sequence;

    /** 用于存放workflowxml */
    private  String workflowxml;


/** 用于存放工作流中节点信息*/
    private List<OozieAction> actions;

   //官方OozieJob中需要用到的字段
    private String User;
    private String ExternalId;
    private String Group;
    private Date LastModifiedTime;
    private String ParentId;
    private Integer Run;
    private Date StartTime;
    private String Acl;
    private String Conf;
    private String ConsoleUrl;

    public void addAction(OozieAction oozieAction) {
        if( this.actions == null ){
            this.actions = new LinkedList<OozieAction>();
        }
        this.actions.add(oozieAction);
    }

//    public  BdaJob toBdaJob(){
//        BdaJob bdaJob=new BdaJob();
//        bdaJob.setOozieJob(this.getJobId());
//        bdaJob.setDescription(this.description);
//        bdaJob.setAccount(this.account);
//        bdaJob.setEndTime(this.endTime);
//        bdaJob.setGraphxml(this.graphxml);
//        bdaJob.setIsExample(this.isExample);
//        bdaJob.setJobName(this.name);
//        bdaJob.setLastSubmitTime(this.endTime);
//        bdaJob.setWorkflowXML(this.workflowxml);
//
//        return bdaJob;
//    }

    public String getWorkflowxml() {
        return workflowxml;
    }

    public void setWorkflowxml(String workflowxml) {
        this.workflowxml = workflowxml;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getGraphxml() {
        return graphxml;
    }

    public String getStatus() {
        return status;
    }

    public String getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Integer getIsExample() {
        return isExample;
    }

    public String getJobId() {
        return jobId;
    }

    public Integer getSequence() {
        return sequence;
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

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setEndTime(Date endTime) {
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

    public List<OozieAction> getActions() {
        return actions;
    }

    public void setActions(List<OozieAction> actions) {
        this.actions = actions;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getExternalId() {
        return ExternalId;
    }

    public void setExternalId(String externalId) {
        ExternalId = externalId;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public Date getLastModifiedTime() {
        return LastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        LastModifiedTime = lastModifiedTime;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public Integer getRun() {
        return Run;
    }

    public void setRun(Integer run) {
        Run = run;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public String getAcl() {
        return Acl;
    }

    public void setAcl(String acl) {
        Acl = acl;
    }

    public String getConf() {
        return Conf;
    }

    public void setConf(String conf) {
        Conf = conf;
    }

    public String getConsoleUrl() {
        return ConsoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        ConsoleUrl = consoleUrl;
    }
}
