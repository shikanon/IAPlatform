package com.ue.service;

import com.ue.entity.Category;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 目录业务接口
 * @Date: created in 16:32 2017/11/10
 */
public interface CategoryService {

    /**
      *@auther: 作者 dzc
      *@description: 类说明 获取目录以及目录下的算法
      *@param: 参数说明 无参
      *@result 结果说明 返回目录列表
      *@exceptions 异常说明 抛出异常
      *@Date: created in 16:34 2017/11/10
      */
    public List<Category> getList(Category category) throws Exception;
}
