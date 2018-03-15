package com.ue.service.serviceImpl;

import com.sun.jersey.api.spring.Autowire;
import com.ue.dao.BaseDao;
import com.ue.entity.Constant;
import com.ue.entity.OozieAction;
import com.ue.entity.ParameterEnum;
import com.ue.entity.Program;
import com.ue.model.TOozieAction;
import com.ue.model.TProgram;
import com.ue.service.OozieActionService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 bk
 * @description: 类说明
 * @Date: created in 10:29 2017/11/16
 */
@Service("oozieActionService")
public class OozieActionServiceImpl implements OozieActionService {

    private static final Logger logger = Logger.getLogger(OozieActionService.class);

    private BaseDao<TOozieAction> oozieActionBaseDao;
    @Autowired
    public void setOozieActionBaseDao(BaseDao<TOozieAction> oozieActionBaseDao){
        this.oozieActionBaseDao=oozieActionBaseDao;
    }

    /**
     *@auther: 作者 bk
     *@description: 类说明   在数据库中插入一个新的对象。
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 17:57 2017/11/15
     */
    @Override
    public Boolean insert(OozieAction oozieaction) {
        logger.info("Service-->新增oozieaction信息");
        TOozieAction tOozieAction = new TOozieAction();
        BeanUtils.copyProperties(oozieaction,tOozieAction);
        Serializable temp = oozieActionBaseDao.save(tOozieAction);
        return true;
    }
    /**
     *@auther: 作者 sigma
     *@description: 类说明   根据action名查询一个对象
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 17:57 2017/11/15
     */
    @Override
    public OozieAction getByname(String oozieActionName) {
        logger.info("Service-->:根据oozieactionname查询");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",oozieActionName);
        TOozieAction tOozieAction = oozieActionBaseDao.get("From TOozieAction where name =:name",params);
        OozieAction oozieAction = null;
        if (tOozieAction != null){
            oozieAction = new OozieAction();
            BeanUtils.copyProperties(tOozieAction,oozieAction);
        } else {
            oozieAction = new OozieAction();
        }
        return oozieAction;
    }

    /**
     * @param oozieActionKey
     * @auther: 作者 sigma
     * @description: 类说明  根据action的主键获取数据信息
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 15:00 2017/11/17
     */
    @Override
    public OozieAction getBykey(int oozieActionKey) {

        logger.info("Service-->:根据主键e查询Action数据");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",oozieActionKey);
        TOozieAction tOozieAction = oozieActionBaseDao.get("From TOozieAction where id =:id",params);
        OozieAction oozieAction = null;
        if (tOozieAction != null){
            oozieAction = new OozieAction();
            BeanUtils.copyProperties(tOozieAction,oozieAction);
        }
        return oozieAction;

    }

    /**
     *@auther: 作者 sigma
     *@description: 类说明   根据action名删除一个对象
     *@param: 参数说明 actionname
     *@result： 结果说明 返回true或者false
     *@exceptions： 异常说明
     *@Date: created in 17:57 2017/11/15
     */
    @Override
    public Boolean delect(String oozieActionName) throws Exception {
        logger.info("Service-->;根据actionname删除一条记录");
        TOozieAction tOozieAction = new TOozieAction();
        tOozieAction.setName(oozieActionName);
        int temp = oozieActionBaseDao.executeHql("delete From TOozieAction toa where toa.name =:name",tOozieAction);
        if (Constant.one == temp){
            return true;
        } else {
            return false;
        }
    }
    /**
     *@auther: 作者 sigma
     *@description: 类说明   根据actionID删除一个对象
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 17:57 2017/11/15
     */

    @Override
    public Boolean delectByOozieJobId(String oozieJobID) throws Exception{
        logger.info("Service-->:根据ooziejobid删除一条记录");
        TOozieAction tOozieAction = new TOozieAction();
        tOozieAction.setOozieJobId(oozieJobID);
        int temp = oozieActionBaseDao.executeHql("delete From TOozieAction toa where toa.oozieJobId =:oozieJobId",tOozieAction);
        if (Constant.one == temp){
            return true;
        } else {
            return false;
        }
    }
    /**
     *@auther: 作者 sigma
     *@description: 类说明   在数据库中插入一个新的对象。
     *@param: 参数说明
     *@result： 结果说明
     *@exceptions： 异常说明
     *@Date: created in 17:57 2017/11/15
     */
    @Override
    public Boolean delectByBdaJobId(String bdaJobId) throws Exception{
        logger.info("Service-->:根据bdaJobId删除一条记录");
        TOozieAction tOozieAction = new TOozieAction();
        tOozieAction.setJobId(bdaJobId);
        int temp = oozieActionBaseDao.executeHql("delete From TOozieAction toa where toa.jobId =:jobId",tOozieAction);
        return true;

    }

    /**
     * @auther: 作者 sigma
     * @description: 类说明  根据oozieID   name  去查询一条actio数据
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:39 2017/11/17
     */
    @Override
    public OozieAction getdateByBdajobidOozieIdName(String OozieJobID,String name) {
   logger.info("Service-->根据BdaID,OozieID,ActionName获取数据库记录");
   Map<String,Object> params=new HashMap<>();
        params.put("OozieJobID",OozieJobID);

        params.put("name",name);

        TOozieAction tOozieAction = oozieActionBaseDao.get("FROM TOozieAction WHERE  ooziejobid =:OozieJobID AND name =:name", params);

        OozieAction oozieAction=null;
        if (tOozieAction!=null){
            oozieAction=new OozieAction();
            BeanUtils.copyProperties(tOozieAction,oozieAction);
        }
        return oozieAction;


    }

    /**

     * @auther: 作者 sigma
     * @description: 类说明  根据oozieID   name  badjobID去更新一条actio数据
     * @param: 参数说明
     * @result： 结果说明  返回新的数据
     * @exceptions： 异常说明
     * @Date: created in 14:57 2017/11/17
     */
    @Override
    public OozieAction update(OozieAction oozieaction) {

        String OozieJobID =oozieaction.getOozieJobId();
        //String BdajobID=oozieaction.getJobId();
        String name=oozieaction.getName();

        OozieAction temp=getdateByBdajobidOozieIdName(OozieJobID,name);
        //将新的对象主键设置为久对象的主键
        oozieaction.setId(temp.getId());
        //将新的对象bdajobid设置为旧的Bdajobid
        oozieaction.setJobId(temp.getJobId());

        TOozieAction tOozieAction=new TOozieAction();
        BeanUtils.copyProperties(oozieaction,tOozieAction);

        oozieActionBaseDao.update(tOozieAction);

        return oozieaction;


        }




}
