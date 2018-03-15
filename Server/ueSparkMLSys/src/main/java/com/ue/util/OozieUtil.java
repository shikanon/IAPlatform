/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.util;




import com.ue.action.JobAction;
import com.ue.entity.OozieAction;
import com.ue.entity.OozieJob;
import org.apache.log4j.Logger;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowAction;
import org.apache.oozie.client.WorkflowJob;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * Support some method to access oozie
 */
public class OozieUtil {
	private static Logger logger = Logger.getLogger(OozieUtil.class
			.getName());
	private static OozieClient wc = new OozieClient(Constants.OOZIE_CLIENT);

	/**
	 * Submit Oozie Job
	 *
	 * @param app_path
	 * @throws OozieClientException
	 * @throws IOException
	 */
	public static String submit(String app_path) throws OozieClientException,
	IOException {
		// 创建工作流作业配置并设置工作流应用程序路径
		Properties conf = wc.createConfiguration();

		conf.setProperty(OozieClient.APP_PATH, app_path);

		// 设置工作流参数
		conf.setProperty("queueName", Constants.QUEUE_NAME);
		conf.setProperty("nameNode", Constants.NAME_NODE);
		conf.setProperty("jobTracker", Constants.JOB_TRACKER);
		conf.setProperty("appPath", app_path);
		String jobId = wc.run(conf);
		logger.info("submit workflow job:" + jobId);

		return jobId;
	}
//
//	/**
//	 * Get job create time
//	 *
//	 * @param jobId job id
//	 * @return create time of job
//	 * @throws OozieClientException
//	 * @author guotianyou
//	 * @time 2015年12月6日上午11:43:22
//	 */
//	public static Date getCreateTime(String jobId) throws OozieClientException {
//		return wc.getBundleJobInfo(jobId).getCreatedTime();
//	}
//
	/**
	 * Consult Oozie job status
	 *
	 * @param ooziejobid
	 * @throws OozieClientException
	 * @throws IOException
	 */
	public static OozieJob getJob(String ooziejobid) throws OozieClientException,
	IOException {
	//String sta=	wc.getJobInfo(jobID).getStatus().toString();
		OozieJob job = setWorkflowjob(wc.getJobInfo(ooziejobid));
		return job;
	}
//
	/**
	 * Kill oozie job
	 *
	 * @param  jobID
	 * @throws OozieClientException
	 * @throws IOException
	 */
	public static void kill(String jobID) throws OozieClientException,
	IOException {

		wc.kill(jobID);
		//更新数据库中的 Oozie表格
		OozieJob job = new OozieJob();
		job.setId(jobID);
//		try {
//			OozieJob temp = SecureDao.getObject(job);
//			if ( temp != null ) {
//				job = temp;
//			}
//			job.setStatus(getJob(jobID).getStatus());
//			System.out.println("[killByOozieJobID Status]" + job.getStatus());
//			String[] setFields = {"status"};
//			String[] condFields = {"jobid"};
//			SecureDao.update(job, setFields, condFields);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
//
	/**
	 * Suspend oozie job
	 *
	 * @param jobID  oozieJob ID
	 * @throws OozieClientException
	 * @throws IOException
	 */
	public static void suspend(String jobID) throws OozieClientException,
	IOException {

		wc.suspend(jobID);
		OozieJob job = new OozieJob();
		//更新数据库
//		job.setId(jobID);
//		try {
//			OozieJob temp = SecureDao.getObject(job);
//			if ( temp != null ) {
//				job = temp;
//			}
//			job.setStatus(getJob(jobID).getStatus());
//			System.out.println("[suspend Status]" + job.getStatus());
//			String[] setFields = {"status"};
//			String[] condFields = {"jobid"};
//			SecureDao.update(job, setFields, condFields);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
//
//	/**
//	 * Rerun oozie job
//	 *
//	 * @param conf
//	 * @throws OozieClientException
//	 * @throws IOException
//	 */
//	/**
//	 * Kill oozie job
//	 *
//	 * @throws OozieClientException
//	 * @throws IOException
//	 */
//	public static void resume(String jobID) throws OozieClientException,
//	IOException {
//
//		wc.resume(jobID);
//		OozieJob job = new OozieJob();
//		job.setId(jobID);
//		try {
//			OozieJob temp = SecureDao.getObject(job);
//			if ( temp != null ) {
//				job = temp;
//			}
//			job.setStatus(getJob(jobID).getStatus());
//			System.out.println("[resume Status]" + job.getStatus());
//			String[] setFields = {"status"};
//			String[] condFields = {"jobid"};
//			SecureDao.update(job, setFields, condFields);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Kill oozie job
//	 *
//	 * @throws OozieClientException
//	 * @throws IOException
//	 */
//	public static void start(String jobID) throws OozieClientException,
//	IOException {
//
//		wc.start(jobID);
//	}
//
//	/**
//	 * Rerun oozie job
//	 *
//	 * @param jobID
//	 * @throws OozieClientException
//	 * @throws IOException
//	 */
//
//	public static void reRun(String jobID) throws OozieClientException,
//	IOException {
//		logger.info("rerun job:" + jobID);
//		// create a workflow job configuration and set the workflow application path
//		Properties conf = wc.createConfiguration();
//		String app_path = wc.getJobInfo(jobID).getAppPath();
//
//		// Need to analyze conf_o
//		conf.setProperty(OozieClient.APP_PATH, app_path);
//		conf.setProperty("queueName", Constants.QUEUE_NAME);
//		conf.setProperty("nameNode", Constants.NAME_NODE);
//		conf.setProperty("jobTracker", Constants.JOB_TRACKER);
//		conf.setProperty("appPath", app_path);
//		conf.setProperty("oozie.wf.rerun.failnodes", "false");
//
//		wc.reRun(jobID, conf);
//		OozieJob job = new OozieJob();
//		job.setId(jobID);
//		try {
//			OozieJob temp = SecureDao.getObject(job);
//			if ( temp != null ) {
//				job = temp;
//			}
//			job.setStatus(getJob(jobID).getStatus());
//			job.setCreatedTime(TimeUtils.getTime());
//
//			logger.info("[reRun Status]" + job.getStatus());
//			String[] setFields = {"status", "createtime", "endtime"};
//			String[] condFields = {"jobid"};
//			SecureDao.update(job, setFields, condFields);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//





	/**
	 * Get oozie's Url
	 *
	 * @param jobID
	 * @return url string
	 * @throws OozieClientException
	 * @throws IOException
	 */
	public static String getUrl(String jobID) throws OozieClientException,
	IOException {
		// get a OozieClient for local Oozie
		String url = wc.getJobInfo(jobID).getAppPath() + "/";
		return url;
	}
//
//	/**
//	 * Copy WorkflowAction information in OOZIE to JobAction we defined
//	 *
//	 * @param temp WorkflowAction in OOZIE
//	 * @param jobid Oozie job id
//	 * @return JobAction
//	 */
//	public static JobAction asAction(WorkflowAction temp, String jobid) {
//		JobAction tempAct = new JobAction();
//		tempAct.setJobId( jobid );
//		tempAct.setName(temp.getName());
//		tempAct.setType(temp.getType());
//		tempAct.setConf(temp.getConf());
//		tempAct.setStatus(temp.getStatus().toString());
//		tempAct.setRetries(temp.getRetries());
//		tempAct.setStartTime(temp.getStartTime());
//		tempAct.setEndTime(temp.getEndTime());
//		tempAct.setTransition(temp.getTransition());
//		tempAct.setData(temp.getData());
//		tempAct.setStats(temp.getStats());
//		tempAct.setExternalChildIDs(temp.getExternalChildIDs());
//		tempAct.setExternalId(temp.getExternalId());
//		tempAct.setExternalStatus(temp.getExternalStatus());
//		tempAct.setTrackerUri(temp.getTrackerUri());
//		tempAct.setConsoleUrl(temp.getConsoleUrl());
//		tempAct.setErrorCode(temp.getErrorCode());
//		tempAct.setErrorMessage(temp.getErrorMessage());
//		return tempAct;
//	}
//
	/**
	 *  将官方定义的WorkflwoJob类转换为自定义的OozieJob类中
	 *
	 *
	 * @param job WorkflowJob
	 * @return OozieJob
	 */
	public static OozieJob setWorkflowjob(WorkflowJob job) {
		OozieJob oozieJob = new OozieJob();
		oozieJob.setAcl(job.getAcl());
		for (WorkflowAction temp : job.getActions()) {
			OozieAction a = asAction(temp, job.getId() );
			oozieJob.addAction(a);
		}
		oozieJob.setName(job.getAppName());
		oozieJob.setPath(job.getAppPath());
		oozieJob.setConf(job.getConf());
		oozieJob.setConsoleUrl(job.getConsoleUrl());
		oozieJob.setCreateTime(job.getCreatedTime());
		oozieJob.setEndTime(job.getEndTime());
		oozieJob.setExternalId(job.getExternalId());
		oozieJob.setGroup(job.getGroup());
		oozieJob.setId(job.getId());
		oozieJob.setLastModifiedTime(job.getLastModifiedTime());
		oozieJob.setParentId(job.getParentId());
		oozieJob.setRun(job.getRun());
		oozieJob.setStartTime(job.getStartTime());
		oozieJob.setStatus(job.getStatus().toString());
		oozieJob.setUser(job.getUser());
		return oozieJob;
	}



	/**
	 *
	 *将官方定义的OOZIE中的工作流操作信息复制到我们定义的OozieAction中
	 * @param temp WorkflowAction in OOZIE
	 * @param ooziejobid  job id
	 * @return OozieAction
	 */
	public static OozieAction asAction(WorkflowAction temp, String ooziejobid) {
		OozieAction tempAct = new OozieAction();
		tempAct.setOozieJobId(ooziejobid);  //setJobId( ooziejobid );
		tempAct.setName(temp.getName());
		tempAct.setType(temp.getType());

		tempAct.setStatus(temp.getStatus().toString());
		tempAct.setRetries(temp.getRetries());
		tempAct.setStartTime(temp.getStartTime());

		tempAct.setEndTime(temp.getEndTime());
		tempAct.setTransition(temp.getTransition());

		tempAct.setStats(temp.getStats());
		tempAct.setExternalChildids(temp.getExternalChildIDs());
		tempAct.setExternalId(temp.getExternalId());
		tempAct.setExternalStatus(temp.getExternalStatus());
		tempAct.setTrackerUri(temp.getTrackerUri());
		tempAct.setConsoleUrl(temp.getConsoleUrl());
		tempAct.setErrorCode(temp.getErrorCode());
		tempAct.setErrorMessage(temp.getErrorMessage());
		return tempAct;
	}

}
