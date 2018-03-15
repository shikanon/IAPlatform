package com.ue.service;


import com.ue.entity.Account;

import java.util.List;

public interface UserService {

	/**
	  *@auther: 作者 dzc
	  *@description: 类说明 查询用户列表
	  *@param: 参数说明 无参
	  *@result 结果说明 返回用户列表
	  *@exceptions 异常说明 抛出异常
	  */
	public List<Account> getList() throws Exception;
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 通过邮箱登录
	 *@param: 参数说明 邮箱
	 *@result 结果说明 返回用户信息
	 *@exceptions 异常说明 抛出异常
	 */
	public Account loginByEmail(String email) throws  Exception;
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 注册用户
	 *@param: 参数说明 用户对象
	 *@result 结果说明 成功返回true失败返回false
	 *@exceptions 异常说明 抛出异常
	 */
	public Boolean registerUser(Account user) throws Exception;
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 更新用户信息
	 *@param: 参数说明
	 *@result 结果说明 返回用户列表
	 *@exceptions 异常说明 抛出异常
	 */
	public Boolean setUserInUse(String email,String power);

	public Boolean setUserNoUse(String email);



}
