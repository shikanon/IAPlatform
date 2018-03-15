package com.ue.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrUpdate(T o);

	public T get(Class<T> c, Serializable id);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> params);

	public List<T> find(String hql, int page, int rows);

	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	public Long count(String hql);

	public Long count(String hql, Map<String, Object> params);

	public int executeHql(String hql);

	/**
	  *@auther: 作者 dzc
	  *@description: 类说明 update和delete的调用
	  *@param: 参数说明 object实体
	  *@result： 结果说明 是否更新成功
	  *@exceptions： 异常说明
	  *@Date: created in 9:49 2017/11/15
	  */
	public int executeHql(String hql,Object object);

	/**
	 *@auther: 作者 dzc
	 *@description: 类说明 根据目录id列表查询相关的算法
	 *@param: 参数说明 目录id列表
	 *@Date: created in 20:04 2017/11/13
	 */
	public List<T> findProgramListByCategoryList(String hql,List<String> categoryList,Map<String, Object> params);

	 /**
	   *@auther: 作者 sigma
	   *@description: 类说明
	   *@param: 参数说明 
	   *@result： 结果说明
	   *@exceptions： 异常说明 
	   *@Date: created in 16:09 2017/11/27
	   */
	public void cleanSessionOne(Object object);
}
