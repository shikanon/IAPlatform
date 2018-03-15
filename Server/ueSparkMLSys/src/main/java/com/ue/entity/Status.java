package com.ue.entity;

import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 14:31 2017/11/30
 */
public class Status {

    private  String  JobStatus;

    private Map   ActionStatus;

 private  BdaJob  bdaJob;


    public Status() {
    }

    public BdaJob getBdaJob() {
        return bdaJob;
    }

    public void setBdaJob(BdaJob bdaJob) {
        this.bdaJob = bdaJob;
    }

    public Status(String jobStatus, Map actionStatus) {
        JobStatus = jobStatus;
        ActionStatus = actionStatus;
    }

    public String getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(String jobStatus) {
        JobStatus = jobStatus;
    }

    public Map getActionStatus() {
        return ActionStatus;
    }

    public void setActionStatus(Map actionStatus) {
        ActionStatus = actionStatus;
    }
}
