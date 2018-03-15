package com.ue.entity;

import java.util.Date;

/**
 * @auther: 作者 dzc
 * @description: 类说明 动作信息表
 * @Date: created in 23:19 2017/11/9
 */
public class OozieAction extends BaseEntity implements java.io.Serializable {

    /**自增主键id*/
    private int id;
    /**该执行动作的id*/
    private String jobId;

    /**执行动作的名称*/
    private String name;

    /**空字段，待定*/
    private String cred;

    /**执行的方式*/
    private String type;

    /**节点的执行情况*/
    private String status;

    /**该动作的重复次数*/
    private Integer retries;

    /**该动作的用户重试次数*/
    private Integer userretryCount;

    /**该动作的用户重试的最大数量*/
    private Integer userretryMax;

    /**用户在几分钟内重试动作的时间间隔*/
    private Integer userretryInterval;

    /**开始执行时间*/
    private Date startTime;

    /**执行完毕时间*/
    private Date endTime;

    /**动作所需要的转换*/
    private String transition;

    /**操作的统计信息*/
    private String stats;

    /**该动作的外部子id*/
    private String externalChildids;

    /**动作的外部id*/
    private String externalId;

    /**动作的外部状态*/
    private String externalStatus;

    /**跟踪动作状态的URL*/
    private String trackerUri;

    /**执行动作的节点的URL*/
    private String consoleUrl;

    /**错误状态码*/
    private String errorCode;

    /**错误信息*/
    private String errorMessage;

    /**工作流的地址*/
    private String appPath;

    /**所属工作流的ID*/
    private String oozieJobId;
    public OozieAction(){};
    public OozieAction (int id,String jobId,String name,String status){
        this.id=id;
        this.jobId=jobId;
        this.name=name;
        this.status=status;
    }
    public String getJobId() {
        return jobId;
    }

    public String getName() {
        return name;
    }

    public String getCred() {
        return cred;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Integer getRetries() {
        return retries;
    }

    public Integer getUserretryCount() {
        return userretryCount;
    }

    public Integer getUserretryMax() {
        return userretryMax;
    }

    public Integer getUserretryInterval() {
        return userretryInterval;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getTransition() {
        return transition;
    }

    public String getStats() {
        return stats;
    }

    public String getExternalChildids() {
        return externalChildids;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getExternalStatus() {
        return externalStatus;
    }

    public String getTrackerUri() {
        return trackerUri;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getAppPath() {
        return appPath;
    }

    public String getOozieJobId() {
        return oozieJobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public void setUserretryCount(Integer userretryCount) {
        this.userretryCount = userretryCount;
    }

    public void setUserretryMax(Integer userretryMax) {
        this.userretryMax = userretryMax;
    }

    public void setUserretryInterval(Integer userretryInterval) {
        this.userretryInterval = userretryInterval;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setTransition(String transition) {
        this.transition = transition;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public void setExternalChildids(String externalChildids) {
        this.externalChildids = externalChildids;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setExternalStatus(String externalStatus) {
        this.externalStatus = externalStatus;
    }

    public void setTrackerUri(String trackerUri) {
        this.trackerUri = trackerUri;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public void setOozieJobId(String oozieJobId) {
        this.oozieJobId = oozieJobId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
