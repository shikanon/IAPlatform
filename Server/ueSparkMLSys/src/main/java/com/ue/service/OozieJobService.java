package com.ue.service;

import com.ue.entity.OozieJob;

import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明 工作流信息处理接口
 * @Date: created in 15:21 2017/11/15
 */
public interface OozieJobService {

     /**
       *@auther: 作者 sigma
       *@description: 类说明  通过ooziejobid  查询某个ooziejob的所有acion的执行状态
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:22 2017/11/30
       */
    public Map getActionsStatus(String ooziejobid);



    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询工作流信息列表
      *@param: 参数说明 无参
      *@result： 结果说明 工作流信息类别
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 17:45 2017/11/15
      */
    public List<OozieJob> getList() throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流信息id查询工作流信息
      *@param: 参数说明 工作流id
      *@result： 结果说明 工作流信息
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 17:47 2017/11/15
      */
    public OozieJob getById(String id) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流id查询工作流信息
      *@param: 参数说明 工作流id
      *@result： 结果说明 工作流信息
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 17:49 2017/11/15
      */
    public List<OozieJob> getListByJobId(String jobId) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 新增工作流信息
      *@param: 参数说明 工作流信息
      *@result： 结果说明 新增是否成功
      *@exceptions： 异常说明 跑出异常
      *@Date: created in 17:56 2017/11/15
      */
    public Boolean addOozieJob(OozieJob oozieJob) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 更新工作流信息
      *@param: 参数说明 工作流信息
      *@result： 结果说明 返回更新状态
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 18:02 2017/11/15
      */
    public Integer updateOozieJob(OozieJob oozieJob) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据工作流id删除工作流信息
      *@param: 参数说明 工作流id
      *@result： 结果说明 删除结果状态 是否成功
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 18:06 2017/11/15
      */
    public Boolean deleteOozieJobById(String id) throws Exception;






}
