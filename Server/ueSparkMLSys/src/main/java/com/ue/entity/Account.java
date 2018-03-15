package com.ue.entity;

/**
 * @auther: 作者 dzc
 * @description: 类说明 用户表实体类
 * @Date: created in 22:55 2017/11/9
 */
public class Account extends BaseEntity implements java.io.Serializable  {

	/**用户注册邮箱*/
	private String email;

	/**用户名*/
	private String userName;

	/**用户密码*/
	private String password;

	/**用户注册时间*/
	private String createTime;

	/**公钥*/
	private String token;

	/**有效时间:有效时间在电子邮件链接用户发送电子邮件注册或修改密码*/
	private String activeTime;

	/**用户所在公司，目前没用到。默认为‘Useease’*/
	private String company;

	/**用户职位，目前没用到。默认为‘Company’*/
	private String position;

	/**默认‘ok’*/
	private String verifyLink;

	/**权限 默认‘111’ */
	private String power;

	public  Account(){};

	public Account (String email,String password,String createTime){
		this.email = email;
		this.password = password;
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getToken() {
		return token;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public String getCompany() {
		return company;
	}

	public String getPosition() {
		return position;
	}

	public String getVerifyLink() {
		return verifyLink;
	}

	public String getPower() {
		return power;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setVerifyLink(String verifyLink) {
		this.verifyLink = verifyLink;
	}

	public void setPower(String power) {
		this.power = power;
	}
}
