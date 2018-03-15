package com.ue.action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ue.dataAnalyze.DataDescribe;
import com.ue.dataAnalyze.DataFeature;
import com.ue.entity.Echar.BarChart;
import com.ue.entity.Echar.Table;
import com.ue.entity.ParameterEnum;
import com.ue.entity.Echar.Radar;
import com.ue.entity.ResultJson;
import com.ue.service.PresentService;
import com.ue.util.HDFSIO;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明  用于展示所用的action
 * @Date: created in 16:14 2017/12/5
 */

@Namespace("/")
@Action(value = "presentAction")
public class PresentAction extends BaseAction{


    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(ProgramAction.class);


    private  String path; //数据对应的地址

    private  String colname;  //数据需要统计的列名称

    private  String ooziejobID; //数据所对应的id

    @Autowired
    private PresentService presentService; //数据预览、展示的接口


     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据某个任务流中的ooziejobid， 以及数据的path， 获得数据的统计基础信息。
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:49 2017/12/28
       */
    public  void getDataDescribe(){
        logger.info("Action-->根据path获取数据的统计信息");
        try {
           DataDescribe result= presentService.getDataDescribe(path,ooziejobID);

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }





     /**
      * （抛弃）
       *@auther: 作者 sigma
       *@description: 类说明  根据path获取数据中指定列colname的统计对象
       *@param: 参数说明
       *@result： 结果说明  使用java多线程。
       *@exceptions： 异常说明
       *@Date: created in 14:28 2017/12/26
       */
    public  void extDataFeature(){
        logger.info("Action-->根据path获取数据中指定列colname的统计对象(已抛弃该action)");
        try {
            DataFeature datafeature= presentService.extDataFeature(this.path,this.colname,"CSV");
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), datafeature);
            super.writeJson(resultJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 /**
   *@auther: 作者 sigma
   *@description: 类说明 画折线图、柱状图所需要的数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 16:26 2018/1/2
   */
    public  void getBarChart(){
        logger.info("Action-->获取线形图数据");

        try {

           BarChart result= presentService.drowLineChar(path,ooziejobID);
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), result);
            super.writeJson(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





 /**
   *@auther: 作者 sigma
   *@description: 类说明 预览数据。 封装成表格对象
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 11:31 2018/1/2
   */
    public  void preview()   {
        logger.info("Action-->数据预览");
        try {
            JsonArray table= presentService.previewDada(path,ooziejobID);

            JsonObject resultJson=new JsonObject();
            resultJson.add("data",table);
            resultJson.addProperty("status",true);
             resultJson.addProperty("message",ParameterEnum.requestSuccess.getDes());

            String resultString=resultJson.toString();

            super.writeJsonString(resultString);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public  static  void  main(String[] args) throws IOException {

        List<String> res= HDFSIO.readAllData("hdfs://hadoop-master:9000///EML/Data/6d1c01df-c87f-44fc-acb4-577056df3ca5",true);
        System.out.println(res);
    }





    //画出 精确度、准确度、F1值、召回率  雷达图，只限于模型评估的节点才可以调用。
    public  void  droeRadar(){

        logger.info("Action-->通过模型评估的节点来画出雷达图");

        Radar radar=new Radar();

        radar.addElement("精确度",0.9);
        radar.addElement("准确度",0.7);
        radar.addElement("召回率",0.2);
        radar.addElement("F1值",0.6);

        radar.setMax(1);

        radar.setName("评估结果");


        ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), radar);
        super.writeJson(resultJson);

    }




    public static Logger getLogger() {
        return logger;
    }


    public String getOoziejobID() {
        return ooziejobID;
    }

    public void setOoziejobID(String ooziejobID) {
        this.ooziejobID = ooziejobID;
    }

    public PresentService getPresentservice() {
        return presentService;
    }

    public void setPresentservice(PresentService presentservice) {
        this.presentService = presentservice;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
