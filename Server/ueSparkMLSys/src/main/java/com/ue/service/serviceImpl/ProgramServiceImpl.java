package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.Constant;
import com.ue.entity.Program;
import com.ue.model.TProgram;
import com.ue.service.ProgramService;
import com.ue.util.Constants;
import com.ue.util.HDFSIO;
import com.ue.util.RunShellGenerator;
import com.ue.util.command.CommandParseException;
import com.ue.util.file.FileUploadServlet;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @auther: 作者 dzc
 * @description: 类说明
 * @Date: created in 15:38 2017/11/10
 */

@Service("programService")
public class ProgramServiceImpl implements ProgramService {

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(ProgramServiceImpl.class);

    //算法持久化dao定义
    private BaseDao<TProgram> programDao;

    public BaseDao<TProgram> getProgramDao() {
        return programDao;
    }

    @Autowired
    public void setProgramDao(BaseDao<TProgram> programDao) {
        this.programDao = programDao;
    }

    /**
     * @param program
     * @auther: 作者 sigma
     * @description: 类说明   将算法上传至hdfs中。并在数据库中添加对应记录
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 10:51 2017/12/22
     */
    @Override
    public Program uploadHDFS(Program program) throws Exception {

        String uuid = UUID.randomUUID().toString();
        String path = Constants.MODULE_PATH + "/" + uuid;
        FileInputStream in = new FileInputStream(program.getUpload());



        //将program上传至hdfs
          //将zip文件上传至hdfs
        HDFSIO.uploadfile("/" + path + "/", new FileInputStream(program.getUpload()), program.getUploadFileName());

          //将zip文件解压后，上传.jar文件到 path/lib里面
        FileUploadServlet.unZipFiles(new FileInputStream(program.getUpload()),path);


        //将run.sh上传至hdfs
        RunShellGenerator gen = new RunShellGenerator();
        String run = gen.generate(program.getCommandLine());
        if (HDFSIO.exist(path + "/run.sh"))
            HDFSIO.delete(path + "/run.sh");
        HDFSIO.upload(path + "/run.sh", run);

        //将program存入数据库
        program.setPath(path);
        program.setId(uuid);
        program.setDeprecated(Constant.zero);
        uploadProgram(program);



//        String uuid = UUID.randomUUID().toString();
//        program.setId(uuid);
//        program.setPath(Constants.MODULE_PATH + "/" +uuid);
//        FileInputStream in = new FileInputStream(program.getUpload());
//
//        HDFSIO.uploadhdfs(uuid,in,uuid);


        return program;

    }





    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询算法列表
      *@param: 参数说明 无参
      *@result 结果说明 返回算法列表
      *@exceptions 异常说明 抛出异常
      *@Date: created in 15:42 2017/11/10
      */
    @Override
    public List<Program> getList() throws Exception{
        logger.info("Service-->获得算法列表");
        List<TProgram> list = programDao.find("from TProgram");
        List<Program> programList = new ArrayList<Program>();
        //javabean和Java实体类直接转换
        changeModel(list,programList);
        return programList;
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据目录id列表查询相对应的算法
      *@param: 参数说明 目录id列表
      *@result 结果说明 返回算法列表
      *@exceptions 异常说明 抛出异常
      *@Date: created in 19:34 2017/11/13
      */
    @Override
    public List<Program> getListByCategoryList(List<String> categoryList) throws Exception{
        logger.info("Service-->根据目录id列表获得算法列表");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("deprecated",new Integer(Constant.zero));
        List<TProgram> list = programDao.findProgramListByCategoryList("SELECT new TProgram(id,name,category) FROM TProgram WHERE deprecated =:deprecated and category IN (:category)",categoryList,params);
        List<Program> programList = new ArrayList<Program>();
        //javabean和Java实体类直接转换
        changeModel(list,programList);
        return programList;

    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 根据算法id查询算法内容
      *@param: 参数说明 算法id
      *@result 结果说明 返回算法实体
      *@exceptions 异常说明 抛出异常
      *@Date: created in 22:28 2017/11/13
      */
    @Override
    public Program getProgramById(String id) throws Exception {
        logger.info("Service-->根据算法id获得算法");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        //"FROM TProgram WHERE id =:id AND name=:name "
        TProgram tProgram = programDao.get("FROM TProgram WHERE id =:id", params);
        Program program = null;
        if (tProgram != null) {
            program = new Program();
            BeanUtils.copyProperties(tProgram, program);
        } else {
            program = new Program();
        }

        return program;
    }

    /**
     * 在批量上传算法的时候，需要根据算法文档中的Id判断是否存在于数据库中。
     *
     * @param UEID
     * @return
     */
    @Override
    public Program getProgramByUeID(String UEID) {

        logger.info("Service-->根据算法文档中的id获得算法");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sourcelink", UEID);
        //"FROM TProgram WHERE id =:id AND name=:name "
        TProgram tProgram = programDao.get("FROM TProgram WHERE sourcelink =:sourcelink", params);
        Program program = null;
        if (tProgram != null) {
            program = new Program();
            BeanUtils.copyProperties(tProgram, program);
        }

        return program;




    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 新增算法
      *@param: 参数说明 算法实体类
      *@result： 结果说明 新增是否成功状态
      *@exceptions： 异常说明 抛出异常
      *@Date: created in 23:41 2017/11/13
      */
    @Override
    public Boolean uploadProgram(Program program) throws Exception {
        //新建一个算法bean
        TProgram tProgram = new TProgram();
        BeanUtils.copyProperties(program, tProgram);

        Serializable temp = programDao.save(tProgram);
        System.out.println(temp.toString());
        if (program.getId().equals(temp.toString())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param id
     * @auther: 作者 dzc
     * @description: 类说明 根据算法id逻辑删除算法
     * @param: 参数说明 算法id
     * @result： 结果说明 逻辑删除是否成功
     * @exceptions： 异常说明 抛出异常
     * @Date: created in 17:20 2017/11/14
     */
    @Override
    public Integer deleteProgramById(String id) throws Exception {
        logger.info("Service-->根据算法id逻辑删除算法");
        //新建算法实体bean
        TProgram tProgram = new TProgram();
        tProgram.setId(id);
        tProgram.setDeprecated(new Integer(Constant.one));

        //执行逻辑删除
      //  "update TProgram tp set tp.deprecated=:deprecated where tp.id=:id",tProgram);
        int temp = programDao.executeHql("update TProgram tp set tp.deprecated=:deprecated where tp.id=:id",tProgram);
        return temp;
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 实体bean和实体类之间的转换
      *@param: 参数说明 表实体,实体bean
      *@Date: created in 16:00 2017/11/10
      */
    private void changeModel(List<TProgram> l, List<Program> nl) {
        if (l != null && l.size() > 0) {
            for (TProgram t : l) {
                Program u = new Program();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }

}


