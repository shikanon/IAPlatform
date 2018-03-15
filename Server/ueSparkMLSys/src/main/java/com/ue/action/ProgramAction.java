package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.ue.entity.Constant;
import com.ue.entity.ParameterEnum;
import com.ue.entity.Program;
import com.ue.entity.ResultJson;
import com.ue.service.ProgramService;
import com.ue.util.command.CommandParseException;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ExecuteAndWaitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @auther: 作者 dzc
 * @description: 类说明 算法表控制中心
 * @Date: created in 11:48 2017/11/10
 */

@Namespace("/")
@Action(value = "programAction")
public class ProgramAction  extends BaseAction implements ModelDriven<Program> {

    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(ProgramAction.class);


    //模型驱动action
    private Program program = new Program();

    @Override
    public Program getModel() {
        return program;
    }

    //算法业务接口实现引入
    private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }

    @Autowired
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }


     /**
       *@auther: 作者 sigma
       *@description: 类说明  上传算法的接口。 上传算法到Hdfs 同时存入数据库
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 14:29 2017/12/22
       */
    public  void   uploadHDFS(){
        logger.info("Action-->上传算法");
        Program prograrm=getModel();

        try {
          Program program=  programService.uploadHDFS(prograrm);
            ResultJson resultJson = new ResultJson(true, ParameterEnum.uploadSuccess.getDes(), program);
            super.writeJson(resultJson);

        } catch (IOException e) {

            e.printStackTrace();
        } catch (CommandParseException e) {
            e.printStackTrace();
        }catch (Exception e){

            ResultJson FALSERESULT= new ResultJson(false, ParameterEnum.uploadFail.getDes(),e);
            super.writeJson( FALSERESULT);
            e.printStackTrace();
        }


    }




    /**
      *@auther: 作者 dzc
      *@description: 类说明 获取算法列表
      *@param: 参数说明
      *@Date: created in 16:10 2017/11/10
      */
    public void getList() {
        logger.info("Action-->获得算法列表");
        List<Program> list = null;
        try {
            list = programService.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(list);

    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据算法id获得算法
      *@param: 参数说明 算法id
      *@Date: created in 23:06 2017/11/13
      */
    public void getProgramById() {
        logger.info("Action-->根据算法id获得算法");
        try {


            Program program = programService.getProgramById(getModel().getId());
            super.writeJson(program);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 上传算法
      *@param: 参数说明 算法实体类
      *@Date: created in 23:26 2017/11/13
      */
    public void uploadProgram() {
        logger.info("Action-->上传算法");
        //模拟接收数据
        //新建uuid
        String uuid = UUID.randomUUID().toString();

        Program program = new Program();
        program.setId(uuid);
        program.setName("dzctest1");
        program.setOwner("dzc1");

        try {
            Boolean flag = programService.uploadProgram(program);
            if (flag) {
                ResultJson resultJson = new ResultJson(true,ParameterEnum.addSuccess.getDes(),new Object());
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
      *@auther: 作者 dzc
      *@description: 类说明 通过算法id删除算法
      *@param: 参数说明 算法id
      *@result： 结果说明 逻辑删除算法是否成功
      *@exceptions： 异常说明 捕获异常
      *@Date: created in 17:17 2017/11/14
      */
    public void deleteProgramById() {
        logger.info("Action-->根据算法id逻辑删除算法");

        try {
            int temp = programService.deleteProgramById(getModel().getId());
            if (temp== Constant.one) {
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
