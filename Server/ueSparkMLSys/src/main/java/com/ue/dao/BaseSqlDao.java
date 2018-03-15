package com.ue.dao;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 直接sql查询
 * @Date: created in 16:29 2017/11/13
 */
public interface BaseSqlDao {

    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询操作
      *@param: 参数说明 sql语句
      *@Date: created in 16:31 2017/11/13
      */
    public List queryBySql(String sql);

    /**
      *@auther: 作者 dzc
      *@description: 类说明 执行操作（增删改）
      *@param: 参数说明 sql语句
      *@Date: created in 16:32 2017/11/13
      */
    public int excuteBySql(String sql);

}
