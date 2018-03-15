package com.ue.entity;

import com.ue.entity.graph.OozieGraph;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 工作流实体类
 * @Date: created in 23:03 2017/11/9
 */
public class BdaJob extends BaseEntity implements java.io.Serializable {

    /**工作流id*/
    private String jobId;

    /**工作流名称*/
    private String jobName;

    /**前端需要画图所需信息*/
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

    /**  oozie所需要的wrokflow*/
    private  String  workflowxml;

   /** useease 前端所需要的图信息  */
    private  String graphmodel;


    private OozieGraph graph;


    private String actionname;

    private List<String> actionNameList;

    private  List<Node> kk;

    public String getGraphmodel() {
        return graphmodel;
    }

    public void setGraphmodel(String graphmodel) {
        this.graphmodel = graphmodel;
    }

    public OozieGraph getGraph() {
        return graph;
    }

    public void setGraph(OozieGraph graph) {
        this.graph = graph;
    }

    public  OozieJob toOozieJob(){
        OozieJob oozieJob=new OozieJob();
        oozieJob.setDescription(this.description);
        oozieJob.setJobId(this.jobId);
        oozieJob.setId(this.oozieJob);
        oozieJob.setAccount(this.account);
        oozieJob.setId(this.oozieJob);
        oozieJob.setName(this.jobName);
        oozieJob.setGraphxml(this.graphxml);
        oozieJob.setWorkflowxml(this.workflowxml);


        return  oozieJob;

    }



    public List<String> getActionNameList() {
        return actionNameList;
    }

    public void setActionNameList(List<String> actionNameList) {
        this.actionNameList = actionNameList;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getWorkflowxml() {
        return workflowxml;
    }

    public void setWorkflowxml(String workflowxml) {
        this.workflowxml = workflowxml;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getGraphxml() {
        return graphxml;
    }

    public String getAccount() {
        return account;
    }

    public String getDescription() {
        return description;
    }

    public String getLastSubmitTime() {
        return lastSubmitTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getIsExample() {
        return isExample;
    }

    public String getOozieJob() {
        return oozieJob;
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
