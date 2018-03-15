package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.ue.entity.*;
import com.ue.service.JobService;
import org.apache.avro.generic.GenericData;
import org.apache.log4j.Logger;
import org.apache.oozie.client.OozieClientException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import java.io.IOException;
import java.util.*;

/**
 * Created by sigma on 2017/11/14.
 */

@Namespace("/")
@Action(value = "jobAction")
public class JobAction extends BaseAction implements ModelDriven<BdaJob> {

    /**
     * @auther: 作者 sigma
     * @description: 类说明  添加带jobService的构造方法是为了使用Junit来测试功能
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 15:12 2017/11/15
     */
    public JobAction(JobService jobService) {
        this.jobService = jobService;
    }

    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(ProgramAction.class);

    private BdaJob bdaJob = new BdaJob();

    @Override
    public BdaJob getModel() {
        return bdaJob;
    }

    //工作流业务接口实现引入
    private JobService jobService;

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明   根据ooziejobid和actionid获取指定节点的打印输出
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:55 2017/12/4
     */
    public void getStdOut() {

        logger.info("Action-->根据ooziejobid和actionid获取指定节点的打印输出");

        BdaJob bdajob = getModel();

        try {
            String out = jobService.getStdout(bdajob.getOozieJob(), bdajob.getActionname());
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), out);
            super.writeJson(resultJson);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据booziejobID和actionid 获取指定的错误日志
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 12:51 2017/12/4
     */
    public void getStrErr() {
        logger.info("Action-->根据bdajobID和actionid 获取指定的错误日志");

        BdaJob bdajob = getModel();

        try {
            String result = jobService.getStderr(bdajob.getOozieJob(), bdajob.getActionname());
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明 点击工作流目录中的工作流时候，需要调用的接口
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:52 2017/11/30
     */
    public void getJobByid() {
        logger.info("Action--> 点击工作流目录中的工作流时候，需要调用的接口");

        BdaJob bdajob = getModel();

        try {
            BdaJob BDAJOB = jobService.getBdajobByID(bdajob.getJobId()); //数据库中的一条数据、包括图信息。。。

            Map actionStatus = jobService.getActionStatusByBdajobID(bdajob.getJobId());   // 节点的每一个action的执行状态。

            String jobStatus = jobService.getJobStatusByBdajobID(bdajob.getJobId());


            Status result = new Status();
            result.setJobStatus(jobStatus);
            result.setBdaJob(BDAJOB);
            result.setActionStatus(actionStatus);

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  通过bdajobID 查询对应的bdajob的执行状态  以及所有actions节点的执行状态  每2秒调用一次
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:15 2017/11/30
     */
    public void getStatueByBdajobID() {

        //jobAction!getStatueByBdajobID.action

        logger.info("Action-->通过bdajobID 查询对应的bdajob的执行状态  以及所有actions节点的执行状态");

        BdaJob bdajob = getModel();


        try {
            String jobStatue = jobService.getJobStatusByBdajobID(bdajob.getJobId());

            Map actionStatus = jobService.getActionStatusByBdajobID(bdajob.getJobId());

            Status status = new Status(jobStatue, actionStatus);


            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), status);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  通过bdajobID 查询对应的bdajob的执行状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:51 2017/11/29
     */
    public void getJobStatusByBdaJobID() {
        logger.info("Action-->通过bdajobID 查询对应的bdajob的执行状态");
        BdaJob bdajob = getModel();

        try {
            String STATUS = jobService.getJobStatusByBdajobID(bdajob.getJobId());
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), STATUS);


            super.writeJson(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据Bdajob的ID返回数据库中的一条Bdajob信息
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:34 2017/11/29
     */
    public void getBdajobById() {
        logger.info("Action--> 根据Bdajob的ID返回数据库中的一条Bdajob信息");

        BdaJob bdajob = getModel();
        try {
            BdaJob bdaJob = jobService.getBdajobByID(bdajob.getJobId());

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), bdaJob);
            super.writeJson(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据OozieJOB的id获取数据信息
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:40 2017/11/20
     */
    public void getOozieJobById() {
        logger.info("Action-->根据id返回OozieJob的信息");

        BdaJob bdajob = getModel();

        try {
            OozieJob oozieJob = jobService.getOozieJobByID(bdajob.getOozieJob());
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), oozieJob);
            super.writeJson(resultJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  获取节点中某个动作的执行状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 12:20 2017/11/16
     */
    public String getActionStatus(String oozieJobId, String ActionId) {
        logger.info("Action-->获取节点执行状态");
        String actionstatus = jobService.getActionStatus(oozieJobId, ActionId);

        ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), actionstatus);
        super.writeJson(resultJson);

        return actionstatus;

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  获取工作流中指定动作的执行状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 12:10 2017/11/16
     */
    public void getOozieJobStatus(String oozieJobId) throws IOException, OozieClientException {
        logger.info("Action-->获取指定工作流的执行状态");

        String status = jobService.getJobStatus(oozieJobId);

        ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), status);
        super.writeJson(resultJson);
    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据oozieJobID暂停一个工作流
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 10:42 2017/11/15
     */
    public void suspend() {

        logger.info("Action-->暂停一个工作流");

        BdaJob bdaJob = getModel(); //前端返回的badjob

        try {
            jobService.suspendJob(bdaJob.getOozieJob());

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), new Object());
            super.writeJson(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }

    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据Bdajobid kiLl集群上的某个任务
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:45 2017/11/30
     */
    public void killByBdajobID() {
        logger.info("Action-->根据Bdajobid kiLl集群上的某个任务");
        BdaJob bdaJob = getModel(); //前端返回的badjob
        ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), new Object());
        super.writeJson(resultJson);
        try {
            jobService.killByBdajobID(bdaJob.getJobId());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据ooziejobId  killByOozieJobID 一个工作流任务
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 10:32 2017/11/15
     */
    public void killByOozieJobID() {


        logger.info("Action--> kill一个工作流");
        BdaJob bdaJob = getModel(); //前端返回的badjob
        try {
            jobService.killByOozieJobID(bdaJob.getOozieJob());
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), new Object());
            super.writeJson(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明   将任务流提交到集群上运行，如果bdaJobid已经存在，则更新工作流，如果不存在，则返回新的工作流。
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 9:37 2017/11/15
     */
    public BdaJob submit() {

        BdaJob bdaJob = getModel(); //前端返回的badjob


        logger.info("Action-->将任务提交到集群上运行");
        BdaJob result = null;
        try {

            result = jobService.submit(bdaJob);
            // //  result = jobService.submit(bdaJob.getJobName(), bdaJob.getJobId(), bdaJob.getWorkflowxml(), bdaJob.getGraphxml(), bdaJob.getAccount(), bdaJob.getDescription());

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.writeJson(result);
        return result;
    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 17:26 2017/11/27
     */
    public BdaJob submitByGraphXML() {

        BdaJob bdaJob = getModel(); //前端返回的badjob


        logger.info("Action-->将任务提交到集群上运行");


        return null;


    }


//    public static void main(String[] args) {
//
//        String json = "[{\"category\":\"08906982-8A78-4C1B-AE16-7F2A15AC9A6B\",\"commandLine\":\"spark-submit --class bda.spark.runnable.formatTransform.LibSVM2LabeledPoint spark.jar --input_pt {in:LibSVM:\\\"LibSVM格式数据文件\\\"} --output_pt {out:LabeledPoint,HFile:\\\"LabeledPoint数据文件\\\"} --is_class [\\\"是否用作分类\\\":Boolean:default,true] \",\"createDate\":\"2017-11-16 10:12:55 AM\",\"deprecated\":0,\"description\":\"\",\"id\":\"0920609F-3B2F-45EF-9CBA-69C8EBAD864A\",\"name\":\"支持向量机分类_Train\",\"owner\":\"sigma@hotmail.com\",\"path\":{\"targetIdList\":[{\"targetId\":\"flowchartWindow1\",\"target_x\":0}],\"sourceIdList\":[]},\"programable\":0,\"scriptVersion\":\"2\",\"type\":\"distributed\",\"version\":\"0.1\",\"domID\":\"flowchartWindow0\",\"inputOut\":[\"output_pt {out:LabeledPoint,HFile:\\\"LabeledPoint数据文件\\\"} \"],\"inputIn\":[\"input_pt {in:LibSVM:\\\"LibSVM格式数据文件\\\"} \"],\"outPostion\":[0.5],\"inPostion\":[0.5],\"nodePOS\":{\"left\":\"0px\",\"top\":\"0px\"}},{\"category\":\"08906982-8A78-4C1B-AE16-7F2A15AC9A6B\",\"commandLine\":\"spark-submit --class bda.spark.runnable.tree.rf.Predict spark.jar --model_pt {in:ObjectFile:\\\"模型文件\\\"} --test_pt {in:LabeledPoint:\\\"测试文件\\\"}   --predict_pt {out:TextFile:\\\"预测文件\\\"}  \",\"createDate\":\"2017-11-10 13:16:41\",\"deprecated\":0,\"description\":\"\",\"id\":\"5CA16CEB-1466-4576-9E22-AFB95E0ACA29\",\"name\":\"随机森林分类_Predict\",\"owner\":\"sigma@hotmail.com\",\"path\":{\"targetIdList\":[],\"sourceIdList\":[{\"sourceId\":\"flowchartWindow0\",\"source_x\":0.5}]},\"programable\":0,\"scriptVersion\":\"2\",\"type\":\"distributed\",\"version\":\"0.1\",\"domID\":\"flowchartWindow1\",\"inputOut\":[\"predict_pt {out:TextFile:\\\"预测文件\\\"}  \"],\"inputIn\":[\"model_pt {in:ObjectFile:\\\"模型文件\\\"} \",\"test_pt {in:LabeledPoint:\\\"测试文件\\\"}   \"],\"outPostion\":[0.5],\"inPostion\":[0,1],\"nodePOS\":{\"left\":\"48px\",\"top\":\"98px\"}}]\n";
//
//        List<Node> list = JSON.parseArray(json, Node.class);
//
//
//        System.out.println();
//        //  list= JSON.parseArray(json.getTasks(), Node.class);
//
//    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明   获取数据库中所有的Bdajob列表
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 16:47 2017/11/14
     */
    public void getBdaJobList() {
        logger.info("Action-->获得bdaJob工作流列表");
        List<BdaJob> list = null;
        try {
            list = jobService.getBdaJobList();

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), list);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(list);

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据id获取工作流状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:08 2017/11/17
     */
    public void getOozieJobstatus() {

        BdaJob bdaJob = getModel(); //前端返回的badjob
        String status = null;
        try {
            status = jobService.getJobStatus(bdaJob.getOozieJob());


            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), status);
            super.writeJson(resultJson);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }

        super.writeJson(status);

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  获取数据库中所有Oozie列表
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 9:32 2017/11/15
     */
    public void getOozieJobList() {
        logger.info("Action-->获得oozieJob工作流列表");
        List<OozieJob> list = null;
        try {
            list = jobService.getOozieJobList();

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), list);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(list);

    }


    public void getOozieActionStatus() {
        logger.info("Action-->获得oozieJob的所有节点执行状态");


        BdaJob bdaJob = getModel();

        try {
            List<OozieAction> actions = jobService.getActions(bdaJob.getOozieJob());

            Map<String, String> result = new HashMap<>();  //<节点名称，节点执行状态>

            for (OozieAction action : actions) {
                result.put(action.getName(), action.getStatus());

            }

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (OozieClientException e) {
            e.printStackTrace();
        }


    }


}
