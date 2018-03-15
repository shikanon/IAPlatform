package com.ue.service;


import com.ue.entity.DataSet;
import com.ue.entity.Program;

import java.io.File;
import java.io.IOException;
import java.util.List;


public interface DataSetService {

    /**
     *@auther: 作者 bk
     *@description: 类说明 查询数据集列表
     *@param: 参数说明 无参
     *@result 结果说明 返回算法列表
     *@exceptions 异常说明 抛出异常
     */
    public List<DataSet> getList()throws Exception;

    /**
     *@auther: 作者 bk
     *@description: 类说明 根据目录id列表查询的数据集
     *@param: 参数说明 目录id列表
     *@result 结果说明 返回算法列表
     *@exceptions 异常说明 抛出异常
     */
    public List<DataSet> getListByCategoryList(List<String> categoryList) throws Exception;

    /**
     *@auther: 作者 bk
     *@description: 类说明 根据算法id查询数据集内容
     *@param: 参数说明 算法id
     *@result 结果说明 返回算法实体
     *@exceptions 异常说明 抛出异常
     */
    public DataSet getDataSetById (String id) throws Exception;

    public void addDataset(DataSet dataSet);

    public void deleteData(DataSet dataSet);


     /**
       *@auther: 作者 sigma
       *@description: 类说明  上传数据文件到HDFS中
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:26 2017/11/21
       */
     public void uploadDataSet(DataSet dataSet, File upload) throws IOException ;


}

