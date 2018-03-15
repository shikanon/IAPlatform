package com.ue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther: 作者 dzc
 * @description: 类说明 动作信息表bean
 * @Date: created in 0:13 2017/11/10
 */

@Entity
@Table(name = "oozieaction", schema = "studio")
public class TOozieAction implements java.io.Serializable {

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
    private String startTime;

    /**执行完毕时间*/
    private String endTime;

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

    /**无参构造函数*/
    public TOozieAction() {
    }

    @Column(name = "jobid", unique = true,nullable = false)
    public String getJobId() {
        return jobId;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "cred")
    public String getCred() {
        return cred;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    @Column(name = "retries")
    public Integer getRetries() {
        return retries;
    }

    @Column(name = "userretrycount")
    public Integer getUserretryCount() {
        return userretryCount;
    }

    @Column(name = "userretrymax")
    public Integer getUserretryMax() {
        return userretryMax;
    }

    @Column(name = "userretryinterval")
    public Integer getUserretryInterval() {
        return userretryInterval;
    }

    @Column(name = "starttime")
    public String getStartTime() {
        return startTime;
    }

    @Column(name = "endtime")
    public String getEndTime() {
        return endTime;
    }

    @Column(name = "transition")
    public String getTransition() {
        return transition;
    }

    @Column(name = "stats")
    public String getStats() {
        return stats;
    }

    @Column(name = "externalchildids")
    public String getExternalChildids() {
        return externalChildids;
    }

    @Column(name = "externalid")
    public String getExternalId() {
        return externalId;
    }

    @Column(name = "externalstatus")
    public String getExternalStatus() {
        return externalStatus;
    }

    @Column(name = "trackeruri")
    public String getTrackerUri() {
        return trackerUri;
    }

    @Column(name = "consoleurl")
    public String getConsoleUrl() {
        return consoleUrl;
    }

    @Column(name = "errorcode")
    public String getErrorCode() {
        return errorCode;
    }

    @Column(name = "errormessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @Column(name = "apppath")
    public String getAppPath() {
        return appPath;
    }

    @Column(name = "ooziejobid")
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
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
