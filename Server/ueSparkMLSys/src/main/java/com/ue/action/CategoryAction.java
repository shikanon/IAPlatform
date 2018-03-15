package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.ue.entity.Category;
import com.ue.entity.Constant;
import com.ue.entity.ParameterEnum;
import com.ue.entity.ResultJson;
import com.ue.intereptor.AuthToken;
import com.ue.service.CategoryService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 目录获控制中心
 * @Date: created in 16:26 2017/11/10
 */

@Namespace("/")
@Action(value = "categoryAction")

public class CategoryAction extends BaseAction implements ModelDriven<Category> {

    //定义日志收集实体
    private static final Logger logger = Logger.getLogger(CategoryAction.class);

    //模型驱动action
    private Category category = new Category();

    @Override
    public Category getModel() {
        return category;
    }

    //算法业务接口实现引入
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 获取目录和目录下的算法
      *@param: 参数说明
      *@Date: created in 16:51 2017/11/10
      */

    public void getList(){
        logger.info("Action-->查询目录结构");
        List<Category> list = null;
        try {
            //判断

            Category category=getModel();

            list = categoryService.getList(category);
            ResultJson resultJson = new ResultJson(true, ParameterEnum.requestSuccess.getDes(),list);
            super.writeJson(resultJson);
        } catch (Exception e) {

            ResultJson FALSERESULT= new ResultJson(false, ParameterEnum.requestFail.getDes(),"");
            super.writeJson( FALSERESULT);

        }
    }
}
