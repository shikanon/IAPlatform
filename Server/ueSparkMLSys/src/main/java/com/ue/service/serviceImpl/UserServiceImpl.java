package com.ue.service.serviceImpl;

import com.ue.dao.BaseDao;
import com.ue.entity.Account;
import com.ue.model.TAccount;
import com.ue.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private BaseDao<TAccount> userDao;

	public BaseDao<TAccount> getAccountDao() {
		return userDao;
	}

	@Autowired
	public void setAccountDao(BaseDao<TAccount> userDao) {
		this.userDao = userDao;
	}

	/**
	  *@auther: 作者 dzc
	  *@description: 类说明 查询用户列表
	  *@param: 参数说明
	  *@result 结果说明 返回用户列表
	  *@exceptions 异常说明 抛出异常
	  *@Date: created in 22:31 2017/11/13
	  */
	@Override
	public List<Account> getList() throws Exception{
		List<TAccount> list = userDao.find("from TAccount");
		List<Account> userList = new ArrayList<Account>();
		changeModel(list,userList);
		return userList;
	}
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 登录
	 *@param: 参数说明
	 *@result 结果说明 返回用户列表
	 *@exceptions 异常说明 抛出异常
	 *@Date: created in 22:31 2017/11/13
	 */
	@Override
	public Account loginByEmail(String email) throws Exception {
		logger.info("Server-->根据email查询用户信息用于登录");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("email",email);
		TAccount tAccount = userDao.get("From TAccount where email =:email",map);
		Account account = null;
		if (tAccount == null){
			return null;
		} else {
			account = new Account();
			BeanUtils.copyProperties(tAccount,account);
			return account;
		}
	}
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 注册
	 *@param: 参数说明
	 *@result 结果说明 返回用户列表
	 *@exceptions 异常说明 抛出异常
	 *@Date: created in 22:31 2017/11/13
	 */
	@Override
	public Boolean registerUser(Account user) throws Exception {
		logger.info("Server-->注册用户，先查询用户是否存在");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("email",user.getEmail());
		TAccount tAccount = userDao.get("From TAccount where email =:email",map);
		if (tAccount == null){
			tAccount = new TAccount();
			BeanUtils.copyProperties(user,tAccount);
			userDao.save(tAccount);
			return true;
		}
		return false;
	}
	/**
	 *@auther: 作者 bk
	 *@description: 类说明 修改用户权限
	 *@param: 参数说明
	 *@result 结果说明 返回用户列表
	 *@exceptions 异常说明 抛出异常
	 *@Date: created in 22:31 2017/11/13
	 */
	@Override
	public Boolean setUserInUse(String email,String power) {
		logger.info("Service-->根据邮箱改变用户状态");
		TAccount tAccount = new TAccount();
		tAccount.setEmail(email);
		tAccount.setPower(power);
		userDao.executeHql("update TAccount tp set tp.power=:power where tp.email=:email",tAccount);
		return true;
	}

	@Override
	public Boolean setUserNoUse(String email) {
		logger.info("Service-->根据邮箱改变用户状态");
		TAccount tAccount = new TAccount();
		tAccount.setEmail(email);
		tAccount.setPower("011");
		userDao.executeHql("update TAccount tp set tp.power=:power where tp.email=:email",tAccount);
		return true;
	}

	/**
	  *@auther: 作者 dzc
	  *@description: 类说明 用户javabean转换成实体类
	  *@param: 参数说明 javabean，实体类
	  *@result 结果说明 无返回
	  *@exceptions 异常说明 抛出异常
	  *@Date: created in 22:32 2017/11/13
	  */
	private void changeModel(List<TAccount> l, List<Account> nl) {
		if (l != null && l.size() > 0) {
			for (TAccount t : l) {
				Account u = new Account();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}
}
