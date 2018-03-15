package com.ue.service.serviceImpl;

import com.ue.entity.Program;
import com.ue.service.BatchUploadService;
import com.ue.service.ProgramService;
import com.ue.test.XlsxUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 18:26 2018/1/2
 */
@Service("batchUploadService")
public class BatchUploadServiceImpl implements BatchUploadService {

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(BatchUploadServiceImpl.class);

    private ProgramService programService;

    public ProgramService getProgramService() {
        return programService;
    }
    @Autowired
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    @Override
    public void uploadPrograrm(File file) throws Exception {


        //String filepath = "input/算法表格.xlsx";

        XlsxUtil excelReader = new XlsxUtil(file);

        File f=new File("E:\\优异科技\\EasyML整理\\ueSparkMLSys\\ueSparkMLSys\\input\\spark.zip");

        String[] title = excelReader.readExcelTitle();

        Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();

        for (int i=1;i<=map.size();i++)
        {
            Program program=new Program();

            String name= (String) map.get(i).get(0);
            String  ueID=(String)map.get(i).get(1);
            String category= (String) map.get(i).get(2);
            String desc= (String) map.get(i).get(3);
            String cmdline= (String) map.get(i).get(4);

            program.setSourceLink(ueID);
            program.setName(name);
            program.setCategory(category);
            program.setDescription(desc);
            program.setCommandLine(cmdline);
            program.setUpload(f);
            //program.setDescription("Distributed");
            program.setOwner("batchupload");
            program.setDeprecated(0);


            /**
             * 判断该算法是否已经存在于数据库中，如果存在了，则不用重复上传该算法。
             */

          Program ExistProgram= programService.getProgramByUeID(ueID);

            if (ExistProgram==null)
            {
                programService.uploadHDFS(program);
            }else {

                logger.info(name+":该算法已经存在，不上传该算法");

            }




            logger.info("成功上传算法："+name);

        }



    }
}
