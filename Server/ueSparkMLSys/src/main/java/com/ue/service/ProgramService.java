package com.ue.service;

import com.ue.entity.Program;
import com.ue.util.command.CommandParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 算法表业务接口
 * @Date: created in 15:37 2017/11/10
 */
public interface ProgramService {

     /**
       *@auther: 作者 sigma
       *@description: 类说明   将算法上传至hdfs中。并在数据库中添加对应记录
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 10:51 2017/12/22
       */
    public  Program  uploadHDFS(Program program) throws Exception;


    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询算法列表
      *@param: 参数说明 无参
      *@result 结果说明 返回算法列表
      *@exceptions 异常说明 抛出异常
      *@Date: created in 15:39 2017/11/10
      */
    public List<Program> getList()throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据目录id列表查询相关的算法
      *@param: 参数说明 目录id列表
      *@result 结果说明 返回算法列表
      *@exceptions 异常说明 抛出异常
      *@Date: created in 19:33 2017/11/13
      */
    public List<Program> getListByCategoryList(List<String> categoryList) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据算法id查询算法内容
      *@param: 参数说明 算法id
      *@result 结果说明 返回算法实体
      *@exceptions 异常说明 抛出异常
      *@Date: created in 22:28 2017/11/13
      */
    public Program getProgramById (String id) throws Exception;


    /**
     *
     * 在批量上传算法的时候，需要根据算法文档中的Id判断是否存在于数据库中。
     *
     * @param UEID
     * @return
     */
    public  Program getProgramByUeID(String UEID);









    /**
      *@auther: 作者 dzc
      *@description: 类说明 新增算法
      *@param: 参数说明 算法实体类
      *@result 结果说明 返回操作结果状态
      *@exceptions 异常说明 抛出异常
      *@Date: created in 23:29 2017/11/13
      */
    public Boolean uploadProgram (Program program) throws Exception;

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据算法id逻辑删除算法
      *@param: 参数说明 算法id
      *@result： 结果说明 逻辑删除是否成功
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 17:20 2017/11/14
      */
    public Integer deleteProgramById(String id) throws Exception;
}
