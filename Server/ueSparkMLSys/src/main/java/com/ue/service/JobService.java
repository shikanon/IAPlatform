package com.ue.service;

import com.ue.entity.BdaJob;
import com.ue.entity.OozieAction;
import com.ue.entity.OozieJob;
import org.apache.oozie.client.OozieClientException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 11:23 2017/11/14
 */
public interface JobService {



     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据bdajobid，在数据库中更新ooziejob的执行状态。
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:29 2017/12/4
       */
    public  String  updateJobStatus(String bdajobid) throws Exception;



     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据bdajobID获取对应的ooziejob的每个action的执行状态
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:18 2017/11/30
       */
    public Map getActionStatusByBdajobID(String bdajobid) throws Exception;




 /**
   *@auther: 作者 sigma
   *@description: 类说明 根据Bdajob的id返回一条bdajob的数据库数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 17:32 2017/11/29
   */
public BdaJob getBdajobByID(String BdajobID) throws Exception;




     /**
       *@auther: 作者 sigma
       *@description: 类说明  通过Graphxml来提交任务
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:27 2017/11/27
       */
    public  void submitByGraphxml(String bdaJobName, String bdaJobId,
                                  String workflow,String graphXML, String account, String desc) throws Exception;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  暂停一个工作
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 10:39 2017/11/15
       */
    public  void  suspendJob(String oozieJobID) throws IOException, OozieClientException;



     /**
       *@auther: 作者 sigma
       *@description: 类说明   killByOozieJobID 一个工作流，根据oozieJobID
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 9:52 2017/11/15
       */
    public void killByOozieJobID(String oozieJobID) throws IOException, OozieClientException;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  killBy  一个工作流，根据bdajobid
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:47 2017/11/30
       */
    public void killByBdajobID(String bdajobid) throws Exception;




     /**
       *@auther: 作者 sigma
       *@description: 类说明  用于提交任务流到集群运行。
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:37 2017/11/14
       */
public BdaJob submit(String bdaJobName, String bdaJobId,
                     String workflow,String graphXML, String account, String desc,String graphmodel) throws Exception;



     /**
       *@auther: 作者 sigma
       *@description: 类说明 传入一个bdaJob类，提交任务到集群运行
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:22 2017/11/28
       */
    public  BdaJob submit(BdaJob bdajob) throws Exception;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  获取数据库中的所有oozie列表
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 12:32 2017/11/14
       */
     public List<OozieJob> getOozieJobList() throws Exception;


      /**
        *@auther: 作者 sigma
        *@description: 类说明  获取数据库中的所有bdajob列表
        *@param: 参数说明
        *@result： 结果说明
        *@exceptions： 异常说明
        *@Date: created in 16:58 2017/11/14
        */
     public List<BdaJob> getBdaJobList();

 /**
   *@auther: 作者 sigma
   *@description: 类说明 根据 bdajob获取工作流的执行状态。 KILL  SUCCESS。。。。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 17:59 2017/11/29
   */
    public String getJobStatusByBdajobID(String bdajobID) throws Exception;



 /**
   *@auther: 作者 sigma
   *@description: 类说明  根据 oozieJobid 获取工作流的执行状态。 KILL  SUCCESS。。。。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 18:28 2017/11/15
   */
    public  String getJobStatus(String  oozieJobid) throws IOException, OozieClientException;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  获取工作流中指定节点的执行状态
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 12:17 2017/11/16
       */
    public  String getActionStatus(String oozieJobID,String ActionID);



     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据jobID 更新其中每一个jobAction的状态
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:21 2017/11/17
       */
    public void updateJobActionStatus(String jobId) ;




 /**
   *@auther: 作者 sigma
   *@description: 类说明  根据OozieJobID、actionID获取某一个action节点的打印输出。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 18:35 2017/11/15
   */
   public  String getStdout(String  OozieJobID,String  actionId) throws IOException, OozieClientException;



 /**
   *@auther: 作者 sigma
   *@description: 类说明 根据OozieJobID、actionID获取某一个action节点的错误日志输出。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 18:36 2017/11/15
   */
    public  String  getStderr(String OozieJobID,String actionId) throws IOException, OozieClientException;


     /**
       *@auther: 作者 sigma
       *@description: 类说明 通过OozieJob的ID从数据库中获取指定的数据集
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:44 2017/11/20
       */
    public OozieJob getOozieJobByID(String OozieJobID) throws Exception;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据OoziejobID获取对应工作流的所有Actions
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 11:24 2017/11/21
       */
    public List<OozieAction> getActions(String OozieJobID) throws IOException, OozieClientException;


    public BdaJob exec(BdaJob bdaJob) throws Exception;

}
