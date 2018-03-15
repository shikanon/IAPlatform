/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.entity;



import com.ue.entity.graph.OozieGraph;
import com.ue.util.*;
 import  com.ue.entity.Script;

import java.util.UUID;

/**
 * BdaJob Oozie Job
 */
public class OozieInstance {


	private BdaJob bdaJob;

	public OozieInstance(BdaJob bdaJob){
		this.bdaJob = bdaJob;
	}

//	public OozieInstance(String bdaJobId) throws Exception{
//		BdaJob query = new BdaJob();
//		query.setJobId(bdaJobId);
//		bdaJob = SecureDao.getObject(query);
//		bdaJob.setOozieGraph(OozieGraphXMLParser.parse(bdaJob.getGraphxml()));
//	}
	public BdaJob exec() throws Exception {
		if( bdaJob == null ) return null;


		OozieGraph graph=bdaJob.getGraph();
		String workflow=bdaJob.getWorkflowxml();
		// 生成并创建oozie的作业应用程序路径
		String app_path = Constants.APP_WORKSPACE + "/APP-PATH-"
				+ UUID.randomUUID().toString();

		HDFSIO.mkdirs(app_path);
		HDFSIO.upload(app_path + "/workflow.xml", workflow);

//		// 如果是脚本或sql脚本程序，它应该将脚本上传到hdfs
		for (Script entry : graph.getScriptList()) {
			String path = entry.getPath().replace("${appPath}", app_path);
			ProgramAbleRunShellGenerator generator = new ProgramAbleRunShellGenerator();
			if (entry.getStartShellPath() != null) {
				String start_script = generator.generate(entry.getInputCount(),
						entry.getOutputCount());
				HDFSIO.upload(
						entry.getStartShellPath().replace("${appPath}",
								app_path), start_script);
			}
			HDFSIO.upload(path, entry.getValue());
		}
//
		// 作为客户端提交一个oozie工作流，返回工作流的id。
		String oozJobId = OozieUtil.submit(app_path);


		// Set the current oozie job id
		//bdaJob.setCurOozJobId(oozJobId);
		bdaJob.setOozieJob(oozJobId);
		bdaJob.setLastSubmitTime(TimeUtils.getTime().toString());

		//*************************************************
		//在数据库中更新bdaJob

//		String cond[] = {"job_id"};
//		String setFields[] = {"oozie_job", "job_name", "account",
//		"last_submit_time"};
//		SecureDao.update(bdaJob, setFields, cond);



		//*************************************************

//*************************************************
		//在数据库中更新所有的oozieaction节点。
		// Every oozie job corresponds to multiple actions,  all the job actions information saved in bda database
//		for (String actionName : graph.getActiveList()) {
//			OozieAction action = new OozieAction();
//			action.setBdaJobId(bdaJob.getJobId());
//			action.setJobId(oozJobId);
//			action.setName(actionName);
//			action.setStatus("new");
//			action.setAppPath(app_path);
//			SecureDao.insert(action);
//		}
//*************************************************

		//*************************************************
		//在ooziejob中更新数据库
			// All oozie jobs information are also saved in bda database
//		OozieJob oozJob = new OozieJob();
//		oozJob.setAppName(bdaJob.getJobName());
//		oozJob.setJobid(bdaJob.getJobId());
//		oozJob.setId(oozJobId);
//		oozJob.setAccount(bdaJob.getAccount());
//		oozJob.setDescription(bdaJob.getDesc());
//		oozJob.setAppPath(app_path);
//		oozJob.setStatus("RUNNING");
//		oozJob.setGraphxml(graph.toXML());
//		SecureDao.insert(oozJob);
//
//		bdaJob.setCurOozJob(oozJob);

		//*************************************************
		return bdaJob;
	}


}
