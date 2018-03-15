package com.ue.service;

import com.ue.entity.OozieAction;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 17:57 2017/11/15
 */
public interface OozieActionService {


     /**
       *@auther: 作者 sigma
       *@description: 类说明   在数据库中插入一个新的对象。
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:57 2017/11/15
       */
    public  Boolean  insert(OozieAction oozieaction);


     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据oozieActionName查询数据库，返回一条数据
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 18:02 2017/11/15
       */
    public OozieAction getByname(String  oozieActionName);

     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据action的主键获取数据信息
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 15:00 2017/11/17
       */
    public  OozieAction getBykey(int oozieActionKey);





     /**
       *@auther: 作者 sigma
       *@description: 类说明  根据Action名称删除指定的Actions 数据
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 18:03 2017/11/15
       */
    public Boolean delect(String oozieActionName) throws Exception;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  条件删除，根据OozieJobId删除指定的数据集
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 18:07 2017/11/15
       */
    public Boolean delectByOozieJobId(String oozieJobID) throws Exception;


     /**
       *@auther: 作者 sigma
       *@description: 类说明  条件删除： 根据bdaJobid 删除指定的数据集
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 18:08 2017/11/15
       */
    public Boolean delectByBdaJobId(String bdaJobId) throws Exception;


 /**
   *@auther: 作者 sigma
   *@description: 类说明  根据oozieID   name 去查询一条actio数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 14:39 2017/11/17
   */
    public  OozieAction getdateByBdajobidOozieIdName(String OozieJobID,String name);


 /**
   *@auther: 作者 sigma
   *@description: 类说明  根据oozieID   name  去更新一条actio数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 14:57 2017/11/17
   */
    public OozieAction update(OozieAction oozieaction);



}
