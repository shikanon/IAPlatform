package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.jersey.api.spring.Autowire;
import com.ue.entity.Constant;
import com.ue.entity.OozieAction;
import com.ue.entity.ParameterEnum;
import com.ue.entity.ResultJson;
import com.ue.service.OozieActionService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther: 作者 bk
 * @description: 类说明 oozieaction表控制中心
 * @Date: created in 11:48 2017/11/10
 */
@Namespace("/")
@Action(value = "oozie2Action")
public class Oozie2Action extends BaseAction implements ModelDriven<OozieAction>{
    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(ProgramAction.class);

    private OozieAction oozieAction = new OozieAction();

    private String name;

    private OozieActionService oozieActionService;
    @Override
    public OozieAction getModel() {
        return oozieAction;
    }
    @Autowired
    public String getName() {
        return name;
    }
    @Autowired
    public void setName(String name) {
        this.name = name;
    }
    public OozieActionService getOozieActionService() {
        return oozieActionService;
    }
    @Autowired
    public void setOozieActionService(OozieActionService oozieActionService) {
        this.oozieActionService = oozieActionService;
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 插入oozieacion
     *@param: 参数说明
     *@Date: created in 23:06 2017/11/13
     */
    public void insert(){
        OozieAction oozieAction = new OozieAction();
        //oozieAction.setId(138);
        oozieAction.setJobId("123");
        oozieAction.setName("456");
        oozieAction.setStatus("ok");
        try {
            Boolean flag = oozieActionService.insert(oozieAction);
            if (flag) {
                ResultJson resultJson = new ResultJson(true, ParameterEnum.addSuccess.getDes(),new Object());
                super.writeJson(resultJson);
            } else {
                ResultJson resultJson = new ResultJson(false,ParameterEnum.addSuccess.getDes(),new Object());
                super.writeJson(resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 根据name获得oozieaction
     *@param: 参数说明
     *@Date: created in 23:06 2017/11/13
     */
//    public void select(){
//        OozieAction oozieAction1 = oozieActionService.select(getModel().getName());
//        System.out.println(getModel().getName());
//        super.writeJson(oozieAction1);
//    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 根据name删除oozieaction
     *@param: 参数说明
     *@Date: created in 23:06 2017/11/13
     */
    public void deleteActionByName(){
        logger.info("Action-->根据name删除oozieaction");

        try {
            Boolean temp = oozieActionService.delect(getModel().getName());
            if (temp== true) {
                ResultJson resultJson = new ResultJson(true,ParameterEnum.delSuccess.getDes(),new Object());
                super.writeJson(resultJson);
            } else {
                ResultJson resultJson = new ResultJson(false,ParameterEnum.delFail.getDes(),new Object());
                super.writeJson(resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 根据ooziejobid删除oozieaction
     *@param: 参数说明
     *@Date: created in 23:06 2017/11/13
     */
    public void deleteActionByOozieJobId(){
        logger.info("Action-->根据ooziejobid删除oozieaction");

        try {
            Boolean temp = oozieActionService.delectByOozieJobId(getModel().getOozieJobId());
            if (temp== true) {
                ResultJson resultJson = new ResultJson(true,ParameterEnum.delSuccess.getDes(),new Object());
                super.writeJson(resultJson);
            } else {
                ResultJson resultJson = new ResultJson(false,ParameterEnum.delFail.getDes(),new Object());
                super.writeJson(resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 根据jobid删除oozieaction
     *@param: 参数说明
     *@Date: created in 23:06 2017/11/13
     */
    public void deleteActionByBdaJobId(){
        logger.info("Action-->根据jobid删除oozieaction");
        try {
            Boolean temp = oozieActionService.delectByBdaJobId(getModel().getJobId());
            if (temp== true) {
                ResultJson resultJson = new ResultJson(true,ParameterEnum.delSuccess.getDes(),new Object());
                super.writeJson(resultJson);
            } else {
                ResultJson resultJson = new ResultJson(false,ParameterEnum.delFail.getDes(),new Object());
                super.writeJson(resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
