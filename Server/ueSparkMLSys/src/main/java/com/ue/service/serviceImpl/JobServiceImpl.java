package com.ue.service.serviceImpl;



import com.ue.entity.*;

import com.ue.entity.graph.OozieGraph;
import com.ue.entity.graph.OozieProgramNode;
import com.ue.entity.workflow.OozieGraphXMLParser;
import com.ue.model.TOozieJob;
import com.ue.service.*;
import com.ue.util.*;
import org.apache.log4j.Logger;
import org.apache.oozie.client.OozieClientException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 11:23 2017/11/14
 */
@Service("jobService")
public class JobServiceImpl implements JobService {

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(JobServiceImpl.class);

    //定义job的服务类
    private OozieJobService oozieJobService;
    private BdaJobService   bdaJobService;
    private OozieActionService oozieActionService;




    public OozieActionService getOozieActionService() {
        return oozieActionService;
    }

    @Autowired
    public void setOozieActionService(OozieActionService oozieActionService) {
        this.oozieActionService = oozieActionService;
    }

    public OozieJobService getOozieJobService() {
        return oozieJobService;
    }

    @Autowired
    public void setOozieJobService(OozieJobService oozieJobService) {
        this.oozieJobService = oozieJobService;
    }

    public BdaJobService getBdaJobService() {
        return bdaJobService;
    }

    @Autowired
    public void setBdaJobService(BdaJobService bdaJobService) {
        this.bdaJobService = bdaJobService;
    }

    //算法持久化dao定义
/*       //oozieJob的dao接口
    private BaseDao<TOozieJob> oozieJobDao;*/
    //bdaJob的dao接口
    //private  BaseDao<TBdaJob> bdaJobDao;


          //bdajobService
         //oozieJobService


    /*public BaseDao<TOozieJob> getOozieJobDao() {
        return oozieJobDao;
    }

    public void setOozieJobDao(BaseDao<TOozieJob> oozieJobDao) {
        this.oozieJobDao = oozieJobDao;
    }*/


    /**
     * @param bdajobid
     * @auther: 作者 sigma
     * @description: 类说明  根据bdajobid，在数据库中更新ooziejob的执行状态。
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:29 2017/12/4
     */
    @Override
    public String updateJobStatus(String bdajobid) throws Exception {

        //查询ooziejob的执行状态




        return null;
    }

    /**
     * @param bdajobid
     * @auther: 作者 sigma
     * @description: 类说明  根据bdajobID获取对应的ooziejob的每个action的执行状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:18 2017/11/30
     */
    @Override
    public Map getActionStatusByBdajobID(String bdajobid) throws Exception {

     BdaJob bdajob=   bdaJobService.getBdaJobById(bdajobid);

        List<OozieAction> actions=   getActions(bdajob.getOozieJob());

        Map<String,String> result=new HashMap<>();  //<节点名称，节点执行状态>

        for (OozieAction action: actions){
            result.put(action.getName(),action.getStatus());
        }


        return result;
    }

    /**
     * @param BdajobID
     * @auther: 作者 sigma
     * @description: 类说明 根据Bdajob的id返回一条bdajob的数据库数据
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:32 2017/11/29
     */
    @Override
    public BdaJob getBdajobByID(String BdajobID) throws Exception {

        BdaJob bdaJob=bdaJobService.getBdaJobById(BdajobID);

        return bdaJob;
    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明  通过Graphxml来提交任务
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:27 2017/11/27
     */
    @Override
    public void submitByGraphxml(String bdaJobName, String bdaJobId,
                                 String workflow,String graphXML, String account, String desc) throws Exception {



        BdaJob job = null;
        if (bdaJobId == null) {   //如果 bdaJobid为空的话，则新建一个工作流。
            job = this.createBdaJob( bdaJobName,  bdaJobId,   workflow, graphXML,  account,  desc);
            bdaJobId = job.getJobId();
        }else {  //如果不为空，则更新对应ID的workflowxml
            job=this.updateBdaJob( bdaJobName,  bdaJobId,   workflow, graphXML,  account,  desc);

        }
        BdaJob execResult=   execOozieJob(job);



    }

    /**
   *@auther: 作者 sigma
   *@description: 类说明  根据oozieJobId 暂停一个工作流
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 10:39 2017/11/15
   */
    @Override
    public void suspendJob(String oozieJobID) throws IOException, OozieClientException {

        OozieUtil.suspend(oozieJobID);


    }

    /**
       *@auther: 作者 sigma
       *@description: 类说明  根据oozieJobID killByOozieJobID 一个工作流，
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 9:53 2017/11/15
       */
    @Override
    public void killByOozieJobID(String oozieJobID) throws IOException, OozieClientException {
        OozieUtil.kill(oozieJobID);
    }

    /**
     * @param bdajobid
     * @auther: 作者 sigma
     * @description: 类说明  killBy  一个工作流，根据bdajobid
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:47 2017/11/30
     */
    @Override
    public void killByBdajobID(String bdajobid) throws Exception {

      BdaJob bdaJob=  bdaJobService.getBdaJobById(bdajobid);

        OozieUtil.kill(bdaJob.getOozieJob());


    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明  用于提交任务流到集群运行。
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:37 2017/11/14
     */
    @Override
    public BdaJob submit(String bdaJobName, String bdaJobId,
                         String workflow,String graphXML, String account, String desc,String gtaphmodel) throws Exception {


        BdaJob job = null;
        if (bdaJobId == null) {   //如果 bdaJobid为空的话，则新建一个工作流。
            job = this.createBdaJob( bdaJobName,  bdaJobId,   workflow, graphXML,  account,  desc);
            bdaJobId = job.getJobId();
        }else {  //如果不为空，则更新对应ID的workflowxml
          job=this.updateBdaJob( bdaJobName,  bdaJobId,   workflow, graphXML,  account,  desc);
        }


       // OozieInstance instant = new OozieInstance(job);
        job = exec(job);

        job.setGraphmodel(gtaphmodel);
        //在数据库中更新bdajob的信息,如果不存在则直接加入数据库


        //这里添加进去中文乱码。    在这边
        bdaJobService.updateBdaJob(job);

       //BdaJob execResult=   execOozieJob(job);


        return job;

    }

    /**
     * @param bdajob
     * @auther: 作者 sigma
     * @description: 类说明 传入一个bdaJob类，提交任务到集群运行
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:22 2017/11/28
     */
    @Override
    public BdaJob submit(BdaJob bdajob) throws Exception {




       BdaJob result= submit(bdajob.getJobName(),bdajob.getJobId(),bdajob.getWorkflowxml(),bdajob.getGraphxml(),bdajob.getAccount(),bdajob.getDescription(),bdajob.getGraphmodel());





        //在数据库中更新 ooziejobAction的节点数据

//        for (String actionName : job.getGraph().getActiveList()) {
//			OozieAction action = new OozieAction();
//            action.setJobId(bdaJobId);
//
//            action.setOozieJobId(job.getOozieJob());
//
//			action.setName(actionName);
//			action.setStatus("new");
//			action.setAppPath(app_path);
//			SecureDao.insert(action);
//		}

        return result;
    }


    /**
     *@auther: 作者 sigma
     *@description: 类说明  获取数据库中的所有oozie列表
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 12:32 2017/11/14
     */
    @Override
    public List<OozieJob> getOozieJobList() throws Exception {
        logger.info("Service-->获取工作流列表");


       List<OozieJob> list = oozieJobService.getList();

        return list;
    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明  从数据库中获取工作流列表
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:27 2017/11/14
     */
    @Override
    public List<BdaJob> getBdaJobList()  {

        logger.info("Service-->获取BdaJob工作流列表");
//        List<TBdaJob> list = bdaJobDao.find("from TBdaJob");
//        List<BdaJob> bdaJobList = new ArrayList<BdaJob>();
//
//
//        //javabean和Java实体类直接转换
//        changeModelBdaJob(list, bdaJobList);

        return null;
    }

    /**
     * @param bdajobID
     * @auther: 作者 sigma
     * @description: 类说明 根据 bdajob获取工作流的执行状态。 KILL  SUCCESS。。。。
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:59 2017/11/29
     */
    @Override
    public String getJobStatusByBdajobID(String bdajobID) throws Exception {

      BdaJob bdajob=  bdaJobService.getBdaJobById(bdajobID);

       String status= getJobStatus(bdajob.getOozieJob());

        //判断新的job状态是否与数据库中一致，如果不一致，则需要修改

        //在数据库中更新Ooziejob的执行状态


//        //g根据bdajob的ooziejobid查询数据库中的状态
       OozieJob  oozieJobtemp=oozieJobService.getById(bdajob.getOozieJob());
//
        if (oozieJobtemp.getStatus()!=status)   //如果不相等，则进行更新。
        {
            oozieJobtemp.setStatus(status);
            oozieJobtemp.setEndTime(TimeUtils.getTime());
            oozieJobService.updateOozieJob(oozieJobtemp);
        }


        return status;
    }

    /**
     *@auther: 作者 sigma
     *@description: 类说明  根据 oozieJobid 获取工作流的执行状态。 KILL  SUCCESS。。。。
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 18:28 2017/11/15
     */
    @Override
    public String getJobStatus(String oozieJobid) throws IOException, OozieClientException {


      OozieJob oozieJob=OozieUtil.getJob(oozieJobid);


        return oozieJob.getStatus();
    }

    /**
     *@auther: 作者 sigma
     *@description: 类说明  获取工作流中指定节点的执行状态
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 12:17 2017/11/16
     */
    @Override
    public String getActionStatus(String oozieJobID, String ActionID) {




        return null;
    }

    /**
            *@auther: 作者 sigma
    *@description: 类说明  根据jobID 更新其中每一个jobAction的状态
    *@param: 参数说明
    *@result： 结果说明
    *@exceptions： 异常说明
    *@Date: created in 14:21 2017/11/17
            */
    @Override
    public void updateJobActionStatus(String ooziejobId) {
        OozieJob job;
        try {
            job = OozieUtil.getJob(ooziejobId);
            // 在数据库中同步action的信息
            for (OozieAction action : job.getActions()) {
                action.setOozieJobId(job.getId());
              oozieActionService.update(action);
               // SecureDao.update(action, new String[]{"jobid","ooziejobid", "name"});
            }
            for (OozieAction action : job.getActions()) {
                action.setAppPath(job.getPath());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     *@auther: 作者 sigma
     *@description: 类说明  根据OozieJobID、actionID获取某一个action节点的打印输出。
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 18:35 2017/11/15
     */
    @Override
    public String getStdout(String OozieJobID, String actionId) throws IOException, OozieClientException {


           String url;

            url = OozieUtil.getUrl(OozieJobID) + actionId;
            String url_out = url + "/stdout";
            return HDFSIO.cat(url_out);


    }

    /**
     *@auther: 作者 sigma
     *@description: 类说明 根据OozieJobID、actionID获取某一个action节点的错误日志输出。
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 18:36 2017/11/15
     */
    @Override
    public String getStderr(String OozieJobID, String actionId) throws IOException, OozieClientException {



        String url = OozieUtil.getUrl(OozieJobID) + actionId;
        String url_err = url + "/stderr";
        return HDFSIO.cat(url_err);


    }

    /**
     * @param OozieJobID
     * @auther: 作者 sigma
     * @description: 类说明 通过OozieJob的ID从数据库中获取指定的数据集
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:44 2017/11/20
     */
    @Override
    public OozieJob getOozieJobByID(String OozieJobID) throws Exception {



        return  oozieJobService.getById(OozieJobID);
    }

    /**
     * @param OozieJobID
     * @auther: 作者 sigma
     * @description: 类说明  根据OoziejobID获取对应工作流的所有Actions
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 11:24 2017/11/21
     */
    @Override
    public List<OozieAction> getActions(String OozieJobID) throws IOException, OozieClientException {

        logger.info("Service-->获取OozieJob对应的Actions");

      OozieJob oozieJob=  OozieUtil.getJob(OozieJobID);

        return oozieJob.getActions();

    }


    /**
   *@auther: 作者 sigma
   *@description: 类说明  在数据库中根据job ID 更新 数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明sub
   *@Date: created in 15:07 2017/11/14
   */
    private BdaJob updateBdaJob(String bdaJobName, String bdaJobId,
                                String workflow,String graphXML, String account, String desc) throws Exception {

        if (bdaJobName == null)
            bdaJobName = "Not Specified";
        BdaJob job = new BdaJob();
        job.setJobName(bdaJobName);
        job.setJobId(bdaJobId);
        job.setGraphxml(graphXML);

        job.setWorkflowxml(workflow);
        job.setAccount(account);
        job.setDescription(desc);
        job.setLastSubmitTime(TimeUtils.getTime().toString());

      BdaJob result= bdaJobService.updateBdaJob(job);


//        String[] cond = {"job_id"};
//        String[] sets = {"job_name", "graphxml", "account", "description",
//                "last_submit_time"};
//
//        //Modify the programs in the graph xml of the bda job to latest
//        OozieGraph tmpGraph = OozieGraphXMLParser.parse(graph.toXML());
//        LinkedList<OozieProgramNode> proNodes =  tmpGraph.getProgramNodes();
//        for(int i=0 ; i < proNodes.size() ; i++)
//        {
//            OozieProgramNode proNode = proNodes.get(i);
//            proNode.setOozJobId("latest");
//            proNodes.set(i, proNode  );
//        }
//        job.setGraphxml(tmpGraph.toXML());
//
//        SecureDao.update(job, sets, cond);
//        job.setOozieGraph(graph);



        return result;
    }




    private BdaJob execOozieJob(BdaJob bdaJob) throws Exception {

        if( bdaJob == null ) return null;



        String workflow=bdaJob.getWorkflowxml();
        // 生成并创建oozie的作业应用程序路径
        String app_path = Constants.APP_WORKSPACE + "/APP-PATH-"
                + UUID.randomUUID().toString();

        HDFSIO.mkdirs(app_path);
        HDFSIO.upload(app_path + "/workflow.xml", workflow);

//		// 如果是脚本或sql脚本程序，它应该将脚本上传到hdfs
//		for (Script entry : graph.getScriptList()) {
//			String path = entry.getPath().replace("${appPath}", app_path);
//			ProgramAbleRunShellGenerator generator = new ProgramAbleRunShellGenerator();
//			if (entry.getStartShellPath() != null) {
//				String start_script = generator.generate(entry.getInputCount(),
//						entry.getOutputCount());
//				HDFSIO.uploadPrograrm(
//						entry.getStartShellPath().replace("${appPath}",
//								app_path), start_script);
//			}
//			HDFSIO.uploadPrograrm(path, entry.getValue());
//		}
//
        // 作为客户端提交一个oozie工作流，返回工作流的id。
        String oozJobId = null;
        try {
            oozJobId = OozieUtil.submit(app_path);
        } catch (OozieClientException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


      //设置bdajob对应的ooziejobID
        bdaJob.setOozieJob(oozJobId);
        bdaJob.setLastSubmitTime(TimeUtils.getTime().toString());


        //*************************************************
        //在数据库中插入bdaJob

        try {
          //  bdaJobService.addBdaJob(bdaJob);
           bdaJobService.updateBdaJob(bdaJob);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //*************************************************

//*************************************************
        //在数据库中更新所有的oozieaction节点。
        //每个oozie作业都对应于多个操作，所有作业操作信息都保存在数据库中
        List<OozieAction> actions=  OozieUtil.getJob(oozJobId).getActions();
        for(OozieAction action :actions){
             action.setJobId(bdaJob.getJobId());
            oozieActionService.insert(action);
        }

//*************************************************

        //*************************************************
        //在ooziejob中更新数据库


        // All oozie jobs information are also saved in bda database
        OozieJob oozJob =  bdaJob.toOozieJob();
         oozJob.setPath(app_path);
		oozJob.setStatus("RUNNING");

       oozieJobService.updateOozieJob(oozJob);


        //*************************************************
        return bdaJob;


    }





    /**
     * @auther: 作者 sigma
     * @description: 类说明  在数据库中创建bdaJob
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:48 2017/11/14
     */
    private BdaJob createBdaJob(String bdaJobName, String bdaJobId,
                                String workflow,String graphXML, String account, String desc) throws Exception {



        if (bdaJobName == null)
            bdaJobName = "Not Specified";
        if (bdaJobId == null)
            bdaJobId = GenerateSequenceUtil.generateSequenceNo() + "-bda";



      workflow =  bdaJobService.getWorkflow(graphXML,bdaJobName);

        OozieGraph oozieGraph= OozieGraphXMLParser.parse( graphXML);


        //在数据库中创建相应的bda作业
        BdaJob job = new BdaJob();
        job.setWorkflowxml(workflow);
        job.setJobName(bdaJobName);
        job.setJobId(bdaJobId);
        job.setGraphxml(graphXML);
        job.setAccount(account);
        job.setDescription(desc);
        job.setLastSubmitTime(TimeUtils.getTime().toString());
        job.setGraph(oozieGraph);

//        //Modify the programs in the graph xml of the bda job to latest
//        OozieGraph tmpGraph = OozieGraphXMLParser.parse(graphXML);
//        LinkedList<OozieProgramNode> proNodes =  tmpGraph.getProgramNodes();
//        for(int i=0 ; i < proNodes.size() ; i++)
//        {
//            OozieProgramNode proNode = proNodes.get(i);
//            proNode.setOozJobId("latest");
//            proNode.setWorkPath("${appPath}/" + proNode.getId() + "/");
//            proNodes.set(i, proNode	);
//        }
//
//        job.setGraphxml(tmpGraph.toXML());


        //*********************************
//        //将bdajob插入数据库
//        try {
//            bdaJobService.addBdaJob(job);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //*********************************

        return job;
    }








    /**
     * @auther: 作者 sigma
     * @description: 类说明 实体bean和实体类之间的转换
     * @param: 参数说明 表实体,实体bean
     * @Date: created in 12:39 2017/11/14
     */
    private void changeModeloozieJob(List<TOozieJob> l, List<OozieJob> nl) {
        if (l != null && l.size() > 0) {
            for (TOozieJob t : l) {
                OozieJob u = new OozieJob();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }


    public BdaJob exec(BdaJob bdaJob) throws Exception {
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
        //在数据库中更新ooziejob
        OozieJob oozieJob=bdaJob.toOozieJob();
        oozieJob.setStatus("RUNNING");
        oozieJob.setPath(app_path);
        oozieJob.setStartTime(TimeUtils.getTime());

        oozieJobService.addOozieJob( oozieJob);




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
