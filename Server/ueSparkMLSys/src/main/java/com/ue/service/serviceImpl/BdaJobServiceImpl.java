package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.BdaJob;
import com.ue.entity.Constant;
import com.ue.entity.Program;
import com.ue.entity.graph.*;
import com.ue.entity.workflow.*;
import com.ue.model.TBdaJob;
import com.ue.service.BdaJobService;

import com.ue.service.ProgramService;
import com.ue.service.WFBuilder;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 15:21 2017/11/15
 */
@Service("bdaJobService")
public class BdaJobServiceImpl  implements BdaJobService{

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(BdaJobServiceImpl.class);

    //BdaJob持久层
    private BaseDao<TBdaJob> bdaJobDao;

   private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }
    @Autowired
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public BaseDao<TBdaJob> getBdaJobDao() {
        return bdaJobDao;
    }

    @Autowired
    public void setBdaJobDao(BaseDao<TBdaJob> bdaJobDao) {
        this.bdaJobDao = bdaJobDao;
    }




    /**
     * @param graphxml
     * @param jobname
     * @auther: 作者 sigma
     * @description: 类说明  根据graphxml和jobname返回workflow
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 11:30 2017/11/29
     */
    @Override
    public String getWorkflow(String graphxml, String jobname) throws Exception {


        OozieGraph oozieGraph= OozieGraphXMLParser.parse( graphxml);

        WFBuilder wfBuilder = buildFromOozieGraph(oozieGraph);

        //为了将数据主体、与统计数据文本隔离开，将数据主体存放在 **/DATA目录下。  将统计结果存放在 **/SAMPLE下。



        String workflow = wfBuilder.asWFGraph().toWorkflow(jobname);

        return workflow;
    }

    /**
     * @param bdajob
     * @auther: 作者 sigma
     * @description: 类说明  通过bdaJob对象 返回workflow.xml
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 11:21 2017/11/29
     */
    @Override
    public String getWorkflow(BdaJob bdajob) throws Exception {

   OozieGraph oozieGraph= OozieGraphXMLParser.parse( bdajob.getGraphxml());

        WFBuilder wfBuilder = buildFromOozieGraph(oozieGraph);

        String workflow = wfBuilder.asWFGraph().toWorkflow(bdajob.getJobName());

        return workflow;

    }

    /**
     * @param bdaJob
     * @auther: 作者 dzc
     * @description: 类说明 新增工作流
     * @param: 参数说明 工作流
     * @result： 结果说明 是否新增成功 true新增成功 false 新增失败
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 19:31 2017/11/16
     */
    @Override
    public BdaJob addBdaJob(BdaJob bdaJob) throws Exception {
        //备注：传入的实体类需要有唯一主键UUID
        logger.info("Service-->新增工作流");
        //新建一个工作流信息bean
        TBdaJob tBdaJob = new TBdaJob();
        //表实体和实体bean的转换
        BeanUtils.copyProperties(bdaJob,tBdaJob);
        //进行插入操作,成功返回数据的唯一主键
        Serializable temp = bdaJobDao.save(tBdaJob);


         return bdaJob;

    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 查询工作流
     * @param: 参数说明 无
     * @result： 结果说明 工作流列表
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 19:33 2017/11/16
     */
    @Override
    public List<BdaJob> getBdaJobList() throws Exception {
        logger.info("Service-->获取工作流列表");
        //查询工作流信息列表
        List<TBdaJob> tBdaJobList = bdaJobDao.find("from TBdaJob");
        List<BdaJob> bdaJobList = new ArrayList<BdaJob>();
        //表实体和实体bean直接转换
        changeModel(tBdaJobList,bdaJobList);
        return bdaJobList;
    }

    /**
     * @param jobId
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流id查询工作流
     * @param: 参数说明 工作流id
     * @result： 结果说明 工作流信息
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 19:34 2017/11/16
     */
    @Override
    public BdaJob getBdaJobById(String jobId) throws Exception {
        logger.info("Service-->根据工作流id获得工作流");
        //设置查询参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("job_id", jobId);
        TBdaJob tBdaJob = bdaJobDao.get("FROM TBdaJob WHERE job_id =:job_id", params);
        BdaJob bdaJob = null;
        //判断有结果，则表实体和实体bean直接转换，否则返回空
        if (tBdaJob != null) {
            bdaJob = new BdaJob();
            BeanUtils.copyProperties(tBdaJob, bdaJob);
        }
        return bdaJob;
    }

    /**
     * @param bdaJob
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流id更新工作流
     * @param: 参数说明 工作流id
     * @result： 结果说明 是否更新成功 true 成功 false 失败
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 19:35 2017/11/16
     */
    @Override
    public BdaJob updateBdaJob(BdaJob bdaJob) throws Exception {
        logger.info("Service-->根据算工作流id更新工作流");
        //先根据工作流id查询工作流信息
        //设置查询参数
        Map<String, Object> params = new HashMap<String, Object>();   //根据id从数据库中获取对象
        params.put("job_id", bdaJob.getJobId());
        TBdaJob tBdaJob = bdaJobDao.get("FROM TBdaJob WHERE job_id =:job_id", params);

        //将参数值存入获取的实体类
        if (tBdaJob!=null) {
            //job_name 工作流名称
            if (bdaJob.getJobName()!=null) {
                tBdaJob.setJobName(bdaJob.getJobName());
            }
            //graphxml 图节点信息
            if (bdaJob.getGraphxml()!=null) {
                tBdaJob.setGraphxml(bdaJob.getGraphxml());
            }
            //account 用户名
            if (bdaJob.getAccount()!=null) {
                tBdaJob.setAccount(bdaJob.getAccount());
            }
            //description 说明
            if (bdaJob.getDescription()!=null) {
                tBdaJob.setDescription(bdaJob.getDescription());
            }
            //last_submit_time 最后提交时间
            if (bdaJob.getLastSubmitTime()!=null) {
                tBdaJob.setLastSubmitTime(bdaJob.getLastSubmitTime());
            }
            //endtime 结束时间
            if (bdaJob.getEndTime()!=null) {
                tBdaJob.setEndTime(bdaJob.getEndTime());
            }
            //is_example 是否例子
            if (bdaJob.getIsExample()!=null) {
                tBdaJob.setIsExample(bdaJob.getIsExample());
            }
            //oozie_job
            if (bdaJob.getOozieJob()!=null) {
                tBdaJob.setOozieJob(bdaJob.getOozieJob());
            }
            //workflow
            if (bdaJob.getWorkflowxml()!=null) {
                tBdaJob.setWorkflowxml(bdaJob.getWorkflowxml());
            }

            if (bdaJob.getGraphmodel()!=null) {
                tBdaJob.setGraphmodel(bdaJob.getGraphmodel());

            }


//            //清除session中的缓存
//            TBdaJob tBdaJob1=new TBdaJob();
//            BeanUtils.copyProperties(bdaJob1,tBdaJob1);
//            bdaJobDao.cleanSessionOne(tBdaJob1);

            //全量更新
          bdaJobDao.update(tBdaJob);
            BdaJob result=new BdaJob();
            BeanUtils.copyProperties(tBdaJob,result);
            return result;
        } else {
            //没有查询到，说明没有对应的数据，直接将数据插入数据表

            return   addBdaJob(bdaJob);
        }


    }

    /**
     * @param jobId
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流id删除工作流
     * @param: 参数说明 工作流id
     * @result： 结果说明 是否删除成功 true 成功 false 失败
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 19:36 2017/11/16
     */
    @Override
    public boolean deleteBdaJobById(String jobId) throws Exception {
        logger.info("Service-->根据工作流id删除工作流");
        //新建工作流表实体
        TBdaJob tOBdaJob = new TBdaJob();
        tOBdaJob.setJobId(jobId);

        //执行删除操作
        int temp = bdaJobDao.executeHql("delete from TBdaJob toj where toj.job_id=:jobId",tOBdaJob);

        //返回删除结果状态
        if (Constant.one == temp) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *@auther: 作者 dzc
     *@description: 类说明 实体bean和实体类之间的转换
     *@param: 参数说明 表实体,实体bean
     *@Date: created in 16:00 2017/11/10
     */
    private void changeModel(List<TBdaJob> l, List<BdaJob> nl) {
        if (l != null && l.size() > 0) {
            for (TBdaJob t : l) {
                BdaJob u = new BdaJob();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }


    /**
     * A {@link WFGraph} should be built from an {@link OozieGraph}
     */
    public WFBuilder buildFromOozieGraph(OozieGraph graph) throws Exception {
        WFBuilder wfBuilder=new WFBuilder();
        WFGraph wfGraph = new WFGraph();

        //first load all program nodes into the WFGraph,
        //and built relations between program nodes
        //according to the edges in OoziGrpah
        loadGraph(graph,  wfGraph );

        //insert start node and end node for Oozie workflow into the DAG
        insertStartAndEnd(wfGraph);

        //insert Fork and Join Action for parallel
        insertForkAndJoin(wfGraph);
        wfBuilder.setWfGraph(wfGraph);

        return  wfBuilder;
    }


    /**
     * Add all {@link OozieProgramNode} in {@link OozieGraph} into {@link WFGraph},
     * parse all file dependencies according to the edges in {@link OozieProgramNode}
     * @param graph the DAG graph
     * @throws Exception
     */
    private void loadGraph(OozieGraph graph, WFGraph wfGraph ) throws Exception {
        for (OozieProgramNode node : graph.getProgramNodes()) {
            if (graph.isActiveNode(node.getId())) {
                NodeDef action = wrapAsNodeDef(node);
                   wfGraph.addActionNode(action);
            }
        }

        Map<String, OozieNode> nodeMap = new HashMap<String,OozieNode>();
        List<String> nodeOutputFiles = null;
        for(OozieDatasetNode node : graph.getDatasetNodes() )
        {
            nodeOutputFiles = new ArrayList<String>();
            nodeMap.put(node.getId(), node);
            if(graph.isActiveNode(node.getId()))
            {
                nodeOutputFiles.add(node.getFile());
                wfGraph.addNodeOutputFile(node.getId(), nodeOutputFiles,null);
            }
        }
        for(OozieProgramNode node : graph.getProgramNodes() )
        {
            nodeOutputFiles = new ArrayList<String>();
            nodeMap.put(node.getId(), node);
            if(graph.isActiveNode(node.getId()))
            {
                nodeOutputFiles.addAll(node.getFiles());
                wfGraph.addNodeOutputFile(node.getId(), nodeOutputFiles,node.getWorkPath());
            }
        }

        for (OozieEdge edge : graph.getEdges()) {
            String src = edge.getSrc();
            String dst = edge.getDst();

            String[] src_tok = src.split(":");
            String[] dst_tok = dst.split(":");

            int srcPort = Integer.parseInt(src_tok[1]);
            int dstPort = Integer.parseInt(dst_tok[1]);

            OozieNode srcNode = nodeMap.get(src_tok[0]);
            if (srcNode == null)
                continue;

            String file = null;
            if (srcNode instanceof OozieDatasetNode)
                file = ((OozieDatasetNode) srcNode).getFile();
            else{
                OozieProgramNode node = ((OozieProgramNode) srcNode);
                file = node.getWorkPath() + node.getFiles().get(srcPort);
            }
            wfGraph.addEdge(src_tok[0], dst_tok[0], srcPort, dstPort, file);
        }
    }




    private void insertStartAndEnd(WFGraph wfGraph ) {
        for (NodeDef node : wfGraph.nodeMap.values()) {
            if (node.getInDegree() == 0) {
                wfGraph.start.addOutNode(node);
                node.addInNode(wfGraph.start);
            }
            if (node.getOutDegree() == 0) {
                node.addOutNode(wfGraph.end);
                wfGraph.end.addInNode(node);
            }
        }

        if (wfGraph.start.getOutDegree() == 0 && wfGraph.end.getInDegree() == 0) {
            wfGraph.start.addOutNode(wfGraph.end);
            wfGraph.end.addInNode(wfGraph.start);
        }
    }




    private void insertForkAndJoin(WFGraph wfGraph ) {
        NodeDef start = wfGraph.start;
        NodeDef end = wfGraph.end;
        Queue<NodeDef> que = new LinkedList<NodeDef>();
        // Insert fork and join node
        NodeDef cur_node = start;
        int count = 0;
        do {
            if (count++ > 10)
                break;
            for (NodeDef suc_node : cur_node.getOutNodes()) {
                suc_node.delInNode(cur_node);
                if (suc_node.getInDegree() == 0) {
                    que.add(suc_node);
                }
            }

            cur_node.clearOutNodes();

            if (que.size() > 1) {// If the out degree greater than 1, then need to insert fork/join node（the node should be used in pair）
                // A fork node splits one path of execution into multiple
                // concurrent
                // paths of execution.
                // A join node waits until every concurrent execution path of a
                // previous
                // fork node arrives to it.
                // The fork and join nodes must be used in pairs. The join node
                // assumes
                // concurrent execution paths
                // are children of the same fork node.
                String uuid = UUID.randomUUID().toString();
                NodeDef fork = new ForkNodeDef("fork-" + uuid);
                NodeDef join = new JoinNodeDef("join-" + uuid);

                // Modify cur_node descent node  to fork node
                buildLink(cur_node, fork);

                while (!que.isEmpty()) {
                    buildLink(fork, que.remove());
                }

                //Insert join node between pre_node and suc_node node
                for (NodeDef pre_node : fork.getOutNodes()) {// For children node of fork node
                    for (NodeDef suc_node : pre_node.getOutNodes()) {// Insert join node between pre_node and suc_node
                        suc_node.delInNode(pre_node);
                        join.addOutNode(suc_node);// Point the join node to suc_node
                    }

                    //Modify pre_node descent node which only include join node
                    pre_node.clearOutNodes();
                    buildLink(pre_node, join);
                }

                // Modify the join descent node's father node to join node
                for (NodeDef suc_node : join.getOutNodes()) {
                    suc_node.addInNode(join);
                }
                cur_node = join;
            } else {
                buildLink(cur_node, que.peek());
                cur_node = que.remove();
            }
        } while (cur_node != end);

    }

    /**
     * Wrap a {@link OozieProgramNode} as {@link NodeDef}
     * @param node
     * @return
     * @throws Exception
     */
    private NodeDef wrapAsNodeDef(OozieProgramNode node) throws Exception {
        NodeDef action = null;

        Program query = new Program();
        query.setId(node.getModuleId());
        System.out.println( node.getModuleId() );

        Program prog =    programService.getProgramById(node.getModuleId());


        if (prog.isDistributed() || prog.isETL()) {
            // If is distribute program
            action = new DistributeActionNodeDef(prog, node.getId(), node.getCmdLine());
        } else {
            action = new ShellActionNodeDef(prog, node.getId(), node.getCmdLine());
        }
        return action;
    }
    /**
     * build link between to nodes
     * @param pre_node the previous node
     * @param suc_node the successor node
     */
    private void buildLink(NodeDef pre_node, NodeDef suc_node) {
        pre_node.addOutNode(suc_node);
        suc_node.addInNode(pre_node);
    }

}
