package com.ue.action;

import com.opensymphony.xwork2.ModelDriven;
import com.ue.entity.Account;
import com.ue.service.UserService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<Account> {

    private static final Logger logger = Logger.getLogger(UserAction.class);

    private Account user = new Account();

    @Override
    public Account getModel() {
        return user;
    }


    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询用户列表
      *@param: 参数说明
      *@Date: created in 22:32 2017/11/13
      */
    public void getList() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        logger.info("Action-->查询用户列表");
        List<Account> list = null;
        try {
            list = userService.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(list);
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 登录
     *@param: 参数说明
     *@Date: created in 22:32 2017/11/13
     */
    public void loginByEmail(){
        String email = "junxu@ict.ac.cn";
        Account account = new Account();
        try {
            account =  userService.loginByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.writeJson(account);
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 注册
     *@param: 参数说明
     *@Date: created in 22:32 2017/11/13
     */
    public void registerUser(){
        Account account = new Account();
        account.setEmail("987654@qq.com");
        account.setPassword("987654");
        account.setCreateTime("201711211612");
        try {
            Boolean flag =  userService.registerUser(account);
            super.writeJson(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *@auther: 作者 bk
     *@description: 类说明 修改用户权限
     *@param: 参数说明
     *@Date: created in 22:32 2017/11/13
     */
    public void updateUserStatus(){
        String email = "987654@qq.com";
        String power = "000";
        Boolean flag = userService.setUserInUse(email,power);
        if (flag == true){
            super.writeJson(flag);
        }
    }
}
