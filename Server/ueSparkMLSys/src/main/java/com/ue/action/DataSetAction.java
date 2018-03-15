package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.ue.entity.DataSet;
import com.ue.entity.ParameterEnum;
import com.ue.entity.Program;
import com.ue.entity.ResultJson;
import com.ue.service.DataSetService;
import com.ue.util.HDFSIO;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;
import com.ue.util.Constants;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Namespace("/")
@Action(value = "dataSetAction")
public class DataSetAction extends BaseAction implements ModelDriven<DataSet>{

    @Autowired
    private DataSetService dataSetService;
    private DataSet dataSet = new DataSet();
    private String pathName;
    private File upload;
   // private String uploadFileName;
    private String uploadContentType;
    public DataSetService getTDataSetService(){
        return this.dataSetService;
    }

    public String getPathName(){
        return this.pathName;
    }

    public void setPathName(String pathName){
        this.pathName = pathName;
    }

    public DataSet getModel() {
        return dataSet;
    }



    /**
     *@auther: 作者 bk
     *@description: 类说明 上传数据到HDFS
     *@param: 参数说明  /tmp/0504e36c-52fd-4291-a5a2-461b755379d4
     *@Date: created in 11:06 2017/11/30
     */
    public void uploadData()   {


        try {
            dataSetService.uploadDataSet(dataSet,upload);

            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(), "");
            super.writeJson(resultJson);
        } catch (IOException e) {


            e.printStackTrace();
        }

//        String uuid = UUID.randomUUID().toString();
//       dataSet.setId(uuid);
//       dataSet.setPath(Constants.DATASET_PATH + "/" +uuid);
//        FileInputStream in = new FileInputStream(uploadPrograrm);
//        HDFSIO.uploadhdfs(uuid,in,uploadFileName);
//       dataSetService.addDataset(dataSet);


    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 根据算法id获得数据集
     *@param: 参数说明 算法id
     *@Date: created in 23:06 2017/11/13
     */
    public void getDataSetById() {
        //logger.info("Action-->根据数据id获得数据");
        try {
            DataSet dataSet = dataSetService.getDataSetById(getModel().getId());
            super.writeJson(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData(){
        dataSetService.deleteData(dataSet);
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }



    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
}
