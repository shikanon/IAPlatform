package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.Constant;
import com.ue.entity.OozieJob;
import com.ue.entity.ParameterEnum;
import com.ue.model.TBdaJob;
import com.ue.model.TOozieJob;
import com.ue.model.TProgram;
import com.ue.service.OozieJobService;
import org.apache.avro.generic.GenericData;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明 工作流操作实现
 * @Date: created in 15:22 2017/11/15
 */

@Service("oozieJobService")
public class OozieJobServiceImpl implements OozieJobService{

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(OozieJobServiceImpl.class);

    //算法持久化dao定义
    private BaseDao<TOozieJob> oozieJobDao;

    public BaseDao<TOozieJob> getOozieJobDao() {
        return oozieJobDao;
    }

    @Autowired
    public void setOozieJobDao(BaseDao<TOozieJob> oozieJobDao) {
        this.oozieJobDao = oozieJobDao;
    }


    /**
     * @param ooziejobid
     * @auther: 作者 sigma
     * @description: 类说明  通过ooziejobid  查询某个ooziejob的所有acion的执行状态
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:22 2017/11/30
     */
    @Override
    public Map getActionsStatus(String ooziejobid) {



        return null;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 查询工作流信息列表
     * @param: 参数说明 无参
     * @result： 结果说明 工作流信息类别
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 17:45 2017/11/15
     */
    @Override
    public List<OozieJob> getList() throws Exception {
        logger.info("Service-->获取工作流信息列表");
        //查询工作流信息列表
        List<TOozieJob> tOozieJobList = oozieJobDao.find("from TOozieJob");
        List<OozieJob> oozieJobList = new ArrayList<OozieJob>();
        //表实体和实体bean直接转换
        changeModel(tOozieJobList,oozieJobList);
        return oozieJobList;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流信息id查询工作流信息
     * @param: 参数说明 工作流id
     * @result： 结果说明 工作流信息
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 17:47 2017/11/15
     */
    @Override
    public OozieJob getById(String id) throws Exception {
        logger.info("Service-->根据工作流信息id获得工作流信息");
        //设置查询参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        TOozieJob tOozieJob = oozieJobDao.get("FROM TOozieJob WHERE id =:id", params);
        OozieJob oozieJob = null;
        //判断有结果，则表实体和实体bean直接转换，否则返回空
        if (tOozieJob != null) {
            oozieJob = new OozieJob();
            BeanUtils.copyProperties(tOozieJob, oozieJob);
        } else {
            oozieJob = new OozieJob();
        }
        return oozieJob;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流id查询工作流信息
     * @param: 参数说明 工作流id
     * @result： 结果说明 工作流信息
     * @exceptions： 异常说明 跑出异常
     * @Date: created in 17:49 2017/11/15
     */
    @Override
    public List<OozieJob> getListByJobId(String jobId) throws Exception {
        logger.info("Service-->根据工作流id获得工作流信息");
        //将参数放入map参数中
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("jobid", jobId);
        //查询结果
        List<TOozieJob> tOozieJobList = oozieJobDao.find("FROM TOozieJob WHERE jobid =:jobid", params);
        //将查询结果转换成实体bean
        List<OozieJob> oozieJobList = new ArrayList<OozieJob>();
        BeanUtils.copyProperties(tOozieJobList, oozieJobList);

        return oozieJobList;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 新增工作流信息
     * @param: 参数说明 工作流信息
     * @result： 结果说明 新增是否成功
     * @exceptions： 异常说明 跑出异常
     * @Date: created in 17:56 2017/11/15
     */
    @Override
    public Boolean addOozieJob(OozieJob oozieJob) throws Exception {
        //备注：传入的实体类需要有唯一主键UUID
        logger.info("Service-->新增工作流信息");
        //新建一个工作流信息bean
        TOozieJob tOozieJob = new TOozieJob();
        //表实体和实体bean的转换
        BeanUtils.copyProperties(oozieJob,tOozieJob);
        //进行插入操作
        Serializable temp = oozieJobDao.save(tOozieJob);

        //判断插入是否成功
        if (oozieJob.getId().equals(temp.toString())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param oozieJob
     * @auther: 作者 dzc
     * @description: 类说明 更新工作流信息
     * @param: 参数说明 工作流信息
     * @result： 结果说明 返回更新状态
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 18:02 2017/11/15
     */
    @Override
    public Integer updateOozieJob(OozieJob oozieJob) throws Exception {
        logger.info("Service-->根据算工作流信息id更新工作流信息");

        //设置查询参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", oozieJob.getId());
        TOozieJob tOozieJob = oozieJobDao.get("FROM TOozieJob WHERE id =:id", params);



        //将参数值存入获取的实体类
        if (tOozieJob!=null) {
            //工作流名称
            if(oozieJob.getName()!=null){
                tOozieJob.setName(oozieJob.getName());
            }
            //工作流地址
            if (oozieJob.getPath()!=null) {
                tOozieJob.setPath(oozieJob.getPath());
            }
            //工作流的图信息
            if (oozieJob.getGraphxml()!=null) {
                tOozieJob.setGraphxml(oozieJob.getGraphxml());
            }
            //工作流的执行状态
            if (oozieJob.getStatus()!=null) {
                tOozieJob.setStatus(oozieJob.getStatus());
            }
            //工作流提交的用户
            if (oozieJob.getAccount()!=null) {
                tOozieJob.setAccount(oozieJob.getAccount());
            }
            //简要说明
            if (oozieJob.getDescription()!=null) {
                tOozieJob.setDescription(oozieJob.getDescription());
            }
            //创建时间
            if (oozieJob.getCreateTime()!=null) {
                tOozieJob.setCreateTime(oozieJob.getCreateTime().toString());
            }
            //工作流结束时间
            if (oozieJob.getEndTime()!=null) {
                tOozieJob.setEndTime(oozieJob.getEndTime().toString());
            }
            //是否例子。默认为‘0’
            if (oozieJob.getIsExample()!=null) {
                tOozieJob.setIsExample(oozieJob.getIsExample());
            }
            //对应的bdajobID
            if (oozieJob.getJobId()!=null) {
                tOozieJob.setJobId(oozieJob.getJobId());
            }
            //序列数，默认为‘0’
            if (oozieJob.getSequence()!=null) {
                tOozieJob.setSequence(oozieJob.getSequence());
            }
        } else {
            //没有查询到，说明没有对应的数据，更新不成功，返回0，即为失败
            //oozieJob;
            TOozieJob tOozieJob1=new TOozieJob();
            BeanUtils.copyProperties(oozieJob,tOozieJob);
            oozieJobDao.save(tOozieJob);

            return Constant.zero;
        }



        //全量更新
        oozieJobDao.update(tOozieJob);

        return Constant.one;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 根据工作流id删除工作流信息
     * @param: 参数说明 工作流id
     * @result： 结果说明 删除结果状态 是否成功
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 18:06 2017/11/15
     */
    @Override
    public Boolean deleteOozieJobById(String id) throws Exception {
        logger.info("Service-->根据工作流信息id删除工作流信息");
        //新建工作流表实体
        TOozieJob tOozieJob = new TOozieJob();
        tOozieJob.setId(id);

        //执行删除操作
        int temp = oozieJobDao.executeHql("delete from TOozieJob toj where toj.id=:id",tOozieJob);

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
    private void changeModel(List<TOozieJob> l, List<OozieJob> nl) {
        if (l != null && l.size() > 0) {
            for (TOozieJob t : l) {
                OozieJob u = new OozieJob();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }
}
