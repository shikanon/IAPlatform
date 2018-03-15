package com.ue.action;

import com.ue.service.BatchUploadService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @auther: 作者 sigma
 * @description: 类说明  批量上传action
 * @Date: created in 16:07 2018/1/3
 */
@Namespace("/")
@Action(value ="batchUploadAction")
public class BatchUploadAction  extends BaseAction {

    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(BatchUploadAction.class);
    private BatchUploadService batchuploadservice;




     /**
       *@auther: 作者 sigma
       *@description: 类说明  批量上传算法。 在算法表格.xlsx中的算法上传到平台中
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 16:07 2018/1/3
       */

    public  void uploadPrograrm(){

        File f=new File("E:\\优异科技\\EasyML整理\\ueSparkMLSys\\ueSparkMLSys\\input\\算法表格.xlsx");

        try {
            batchuploadservice.uploadPrograrm(f);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public BatchUploadService getBatchuploadservice() {
        return batchuploadservice;
    }
    @Autowired
    public void setBatchuploadservice(BatchUploadService batchuploadservice) {
        this.batchuploadservice = batchuploadservice;
    }
}
