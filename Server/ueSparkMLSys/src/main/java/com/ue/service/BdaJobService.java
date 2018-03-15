package com.ue.service;

import com.ue.entity.BdaJob;
import com.ue.entity.graph.OozieGraph;
import org.dom4j.DocumentException;

import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明   用于操作BdaJob数据表
 * @Date: created in 15:21 2017/11/15
 */
public interface BdaJobService {




 /**
   *@auther: 作者 sigma
   *@description: 类说明  根据graphxml和jobname返回workflow
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 11:30 2017/11/29
   */
    public String getWorkflow(String graphxml,String jobname) throws Exception;



 /**
   *@auther: 作者 sigma
   *@description: 类说明  通过bdaJob对象 返回workflow.xml   需要使用的属性为getGraphxml 和jobname
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 11:21 2017/11/29
   */
    public String getWorkflow(BdaJob  bdajob) throws Exception;





    /**
      *@auther: 作者 dzc
      *@description: 类说明 新增工作流
      *@param: 参数说明 工作流
      *@result： 结果说明 是否新增成功 true新增成功 false 新增失败
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 19:31 2017/11/16
      */
    public BdaJob addBdaJob(BdaJob bdaJob) throws  Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询工作流
      *@param: 参数说明 无
      *@result： 结果说明 工作流列表
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 19:33 2017/11/16
      */
    public List<BdaJob> getBdaJobList() throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流id查询工作流
      *@param: 参数说明 工作流id
      *@result： 结果说明 工作流信息
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 19:34 2017/11/16
      */
    public BdaJob getBdaJobById(String jobId) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流更新工作流 ,如果数据库中没有该数据，则直接将数据集插入数据表
      *@param: 参数说明 工作流
      *@result： 结果说明 是否更新成功 true 成功 false 失败
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 19:35 2017/11/16
      */
    public BdaJob updateBdaJob(BdaJob bdaJob) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流id删除工作流
      *@param: 参数说明 工作流id
      *@result： 结果说明 是否删除成功 true 成功 false 失败''
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 19:36 2017/11/16
      */
    public boolean deleteBdaJobById(String jobId) throws Exception;






}
