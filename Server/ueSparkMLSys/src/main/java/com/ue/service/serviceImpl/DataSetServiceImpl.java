package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.*;
import com.ue.entity.graph.OozieGraph;
import com.ue.model.TDataSet;
import com.ue.model.TProgram;
import com.ue.service.DataSetService;
import com.ue.service.JobService;
import com.ue.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;

@Service("dataSetService")
public class DataSetServiceImpl implements DataSetService {

    //定义日志输出类
    private static final Logger logger = Logger.getLogger(DataSetService.class);

    private BaseDao<TDataSet> dataSetDao;

    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Autowired
    public void seTDataSetDao(BaseDao<TDataSet> dataSetDao) {
        this.dataSetDao = dataSetDao;
    }

      /**
        *@auther: 作者 bk
        *@description: 类说明 查询数据集列表
        *@param: 参数说明 无参
        *@result： 结果说明 返回数据集列表
        *@exceptions： 异常说明 抛出异常
        *@Date: created in 11:40 2017/11/14
        */
    @Override
    public List<DataSet> getList() throws Exception {
        logger.info("Service-->获得数据集列表");
        List<TDataSet> list = dataSetDao.find("from TDataSet");
        List<DataSet> datasetlist = new ArrayList<DataSet>();
        //BeanUtils.copyProperties(list,datasetlist);
        changeModel(list,datasetlist);
        return datasetlist;
    }
      /**
        *@auther: 作者 bk
        *@description: 类说明 根据目录查询相关数据集
        *@param: 参数说明 无参
        *@result： 结果说明 返回目录下相关数据集列表
        *@exceptions： 异常说明 抛出异常
        *@Date: created in 11:52 2017/11/14
        */
    @Override
    public List<DataSet> getListByCategoryList(List<String> categoryList) throws Exception {
        logger.info("Service-->根据目录id获得数据集列表");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("deprecated",new Integer(Constant.zero));
        List<TDataSet> list = dataSetDao.findProgramListByCategoryList("SELECT new TDataSet(id,name,category) FROM TDataSet WHERE deprecated =:deprecated and category IN (:category)",categoryList,params);
        List<DataSet> datasetlist = new ArrayList<DataSet>();
        changeModel(list,datasetlist);
       // BeanUtils.copyProperties(list,datasetlist);
        return datasetlist;
    }
      /**
        *@auther: 作者 bk
        *@description: 类说明 根据id查询相关数据集
        *@param: 参数说明   数据集id
        *@result： 结果说明 相关数据集
        *@exceptions： 异常说明 抛出异常
        *@Date: created in 11:55 2017/11/14
        */
    @Override
    public DataSet getDataSetById(String id) throws Exception {
        logger.info("Service-->根据数据集id获取数据集详细信息");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        TDataSet tDataSet = dataSetDao.get("from TDataSet where id =:id",params);
        DataSet dataset = null;
        if (tDataSet != null){
            dataset = new DataSet();
            BeanUtils.copyProperties(tDataSet,dataset);
        } else {
            dataset = new DataSet();
        }
        return dataset;
    }
      /**
        *@auther: 作者 bk
        *@description: 类说明 上传数据集
        *@param: 参数说明 数据集对象
        *@result： 结果说明 无返回值
        *@exceptions： 异常说明
        *@Date: created in 17:08 2017/11/14
        */
    @Override
    public void addDataset(DataSet dataSet) {
        TDataSet t = new TDataSet();
        BeanUtils.copyProperties(dataSet, t);
        dataSetDao.save(t);
    }
      /**
        *@auther: 作者 bk
        *@description: 类说明 删除数据集
        *@param: 参数说明
        *@result： 结果说明
        *@exceptions： 异常说明
        *@Date: created in 17:04 2017/11/14
        */
    @Override
    public void deleteData(DataSet dataSet) {
        TDataSet t = new TDataSet();
        BeanUtils.copyProperties(dataSet, t);
        dataSetDao.delete(t);
    }

    /**
     * @param dataSet
     * @auther: 作者 sigma
     * @description: 类说明  上传数据文件到HDFS中
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:26 2017/11/21
     */

    public void uploadDataSet(DataSet dataSet,File upload) throws IOException {


        String uuid = UUID.randomUUID().toString();
        dataSet.setId(uuid);
        dataSet.setPath(Constants.DATASET_PATH + "/" +uuid);
        FileInputStream in = new FileInputStream(upload);
        HDFSIO.uploadhdfs(uuid,in,uuid);


        //数据上传成功后，需要执行该脚本，使得其开始统计数据的基本特征：标准差、均值、最大值、最小值等



        addDataset(dataSet);

    }

    /**
        *@auther: 作者 bk
        *@description: 类说明 表实体,实体bean直接转换
        *@param: 参数说明
        *@result： 结果说明
        *@exceptions： 异常说明
        *@Date: created in 17:04 2017/11/14
        */
    private void changeModel(List<TDataSet> l, List<DataSet> nl) {
        if (l != null && l.size() > 0) {
            for (TDataSet t : l) {
                DataSet u = new DataSet();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }


    private BdaJob  submitAnalyzeJob(BdaJob bdaJob) throws Exception {



            if( bdaJob == null ) return null;


            OozieGraph graph=bdaJob.getGraph();
            String workflow=bdaJob.getWorkflowxml();
            // 生成并创建oozie的作业应用程序路径
            String app_path = Constants.APP_WORKSPACE + "/APP-PATH-"
                    + UUID.randomUUID().toString();

            HDFSIO.mkdirs(app_path);
            HDFSIO.upload(app_path + "/workflow.xml", workflow);


            // 作为客户端提交一个oozie工作流，返回工作流的id。
            String oozJobId = OozieUtil.submit(app_path);


            // Set the current oozie job id
            //bdaJob.setCurOozJobId(oozJobId);
            bdaJob.setOozieJob(oozJobId);
            bdaJob.setLastSubmitTime(TimeUtils.getTime().toString());


            return bdaJob;
        }


    }







