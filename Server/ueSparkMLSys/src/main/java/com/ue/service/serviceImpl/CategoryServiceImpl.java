package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.*;
import com.ue.model.TCategory;
import com.ue.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 目录业务操作实现
 * @Date: created in 16:35 2017/11/10
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    //目录持久化dao定义
    private BaseDao<TCategory> categoryDao;

    //直接执行sql语句的持久层
    /*@Autowired
    private BaseSqlDao baseSqlDao;*/

    public BaseDao<TCategory> getCategoryDao() {
        return categoryDao;
    }

    @Autowired
    public void setCategoryDao(BaseDao<TCategory> categoryDao) {
        this.categoryDao = categoryDao;
    }

    //算法业务接口实现引入
    private ProgramService programService;
    //工作流结构实现引入
    private JobService jobService;

    private OozieJobService oozieJobService;
    //数据集接口实现引入
    private DataSetService dataSetService;

    public DataSetService getDataSetService() {
        return dataSetService;
    }

    @Autowired
    public void setDataSetService(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    public JobService getJobService() {
        return jobService;
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public ProgramService getProgramService() {
        return programService;
    }

    @Autowired
    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public OozieJobService getOozieJobService() {
        return oozieJobService;
    }

    @Autowired
    public void setOozieJobService(OozieJobService oozieJobService) {
        this.oozieJobService = oozieJobService;
    }
    /**
     * @auther: 作者 dzc
     * @description: 类说明 获取目录及目录下的算法列表
     * @param: 参数说明 无参
     * @result 结果说明 返回目录列表
     * @exceptions 异常说明 抛出异常
     * @Date: created in 16:35 2017/11/10
     */
    @Override
    public List<Category> getList(Category cate) throws Exception {
        logger.info("service-->查询所有目录列表");
        List<TCategory> tCategoryList = categoryDao.find("SELECT new TCategory(id,name,level,type,path) from TCategory");
        List<Category> categoryList = new ArrayList<Category>();
        changeTCategoryToCategory(tCategoryList, categoryList);

        //定义目录列表id集合
        List<String> categoryIdList = new ArrayList<String>();

        if (categoryList != null && categoryList.size() > 0) {
            for (Category category : categoryList) {
                //二级菜单初始化
                category.setChildTree(new ArrayList<Category>());
                //算法列表初始化
                category.setProgramList(new ArrayList<Program>());
                //数据列表初始化TODO 白坤
                category.setDataSetList(new ArrayList<DataSet>());
                //job列表初始化TODO 钢鑫
                category.setOozieJobList(new ArrayList<OozieJob>());

                //id集合
                categoryIdList.add(category.getId());
            }
        }

        //通过目录id列表获取相对应的算法列表
        List<Program> programList = programService.getListByCategoryList(categoryIdList);

        //通过目录id列表获取相对应的数据列表 白坤
        List<DataSet> dataSetList = dataSetService.getListByCategoryList(categoryIdList);

        //通过目录id列表获取相对应的job列表 钢鑫
       // List<OozieJob> oozieJobList = jobService.getOozieJobList();
        List<OozieJob> oozieJobList = oozieJobService.getList();



        //先将算法放入所属的目录下
        if (programList != null && programList.size() > 0) {
            for (Program program : programList) {
                for (Category category : categoryList) {
                    //判读算法所属的目录id和目录id是否相同
                    if (program.getCategory().equals(category.getId())) {
                        List<Program> list = category.getProgramList();
                        list.add(program);
                        category.setProgramList(list);
                        break;
                    }
                }
            }
        }
        //先将数据集放入所属的目录下
        if (dataSetList !=null && dataSetList.size() > 0){
            for (DataSet dataSet : dataSetList){
                for (Category category : categoryList){
                    if (dataSet.getCategory().equals(category.getId())){
                        List<DataSet> list = category.getDataSetList();
                        list.add(dataSet);
                        category.setDataSetList(list);
                        break;
                    }
                }
            }
        }
        //将工作流按照状态放在对应的目录上   //SUCCEEDED  KILLED  RUNNING
        if (oozieJobList != null && oozieJobList.size()>0){
            for (OozieJob job: oozieJobList){
                //在目录中寻找对应的类
                for (Category category : categoryList){
                    if (category.getName().equals(job.getStatus())){
                        List<OozieJob>list=category.getOozieJobList();
                        list.add(job);
                        category.setOozieJobList(list);
                        break;
                    }
                }
            }
        }




        //将目录按照等级来区分,先定义一级和二级菜单list
        List<Category> oneList = new ArrayList<Category>();
        List<Category> twoList = new ArrayList<Category>();
        logger.info("servie-->开始将目录根据结构划分");
        if (categoryList != null && categoryList.size() > 0) {
            //先区分出一级菜单
            for (Category category : categoryList) {
                if (ParameterEnum.ONE.getDes().equals(category.getLevel())) {
                    oneList.add(category);
                } else {
                    twoList.add(category);
                }
            }

            //判断二级目录属于哪个一级目录就放入哪个一级目录下
            for (Category category : twoList) {
                //将路径根据"<"拆分成数组
                String[] s = category.getPath().split(">");

                for (Category category1 : oneList) {
                    if (s.length > 0) {
                        //将符合一级目录的二级目录合并到一级目录里面，成功之后跳出循环
                        if (category1.getName().equals(s[0])) {
                            List<Category> childTree = category1.getChildTree();
                            childTree.add(category);
                            category1.setChildTree(childTree);
                            break;
                        }
                    }
                }
            }
        }

        logger.info("servie-->目录列表根据等级划分成功，返回新的结果");
        return oneList;
    }

    /**
     * @auther: 作者 dzc
     * @description: 类说明 表实体,实体bean之间的转换
     * @param: 参数说明
     * @result 结果说明 无返回
     * @exceptions 异常说明 抛出异常
     * @Date: created in 16:00 2017/11/10
     */
    private void changeTCategoryToCategory(List<TCategory> l, List<Category> nl) {
        if (l != null && l.size() > 0) {
            for (TCategory t : l) {
                Category u = new Category();
                BeanUtils.copyProperties(t, u);
                nl.add(u);
            }
        }
    }



}
