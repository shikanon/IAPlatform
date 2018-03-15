package com.ue.intereptor;

import org.aspectj.lang.JoinPoint;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 13:11 2017/12/21
 */
public class AuthTokenAOPInterceptor {



    private static final String authFieldName = "authToken";
    private static final String userIdFieldName = "userId";

    public void before(JoinPoint joinPoint, AuthToken authToken) throws Throwable{

        Object[] args =  joinPoint.getArgs(); //获取拦截方法的参数
        boolean isFound = false;
        for(Object arg : args){
            if(arg != null){
                Class<?> clazz = arg.getClass();//利用反射获取属性值
                Field[]  fields =  clazz.getDeclaredFields();
                int authIndex = -1;
                int userIdIndex = -1;
                for(int i = 0; i < fields.length; i++){
                    Field field = fields[i];
                    field.setAccessible(true);
                    if(authFieldName.equals(field.getName())){//包含校验Token
                        authIndex = i;
                    }else if(userIdFieldName.equals(field.getName())){//包含用户Id
                        userIdIndex = i;
                    }
                }

                if(authIndex >= 0 & userIdIndex >= 0){
                    isFound = true;
                   // authTokenCheck(fields[authIndex], fields[userIdIndex], arg, authToken);//校验用户
                    break;
                }
            }
        }
        if(!isFound){
            //throw new// BizException(ErrorMessage.CHECK_AUTHTOKEN_FAIL);
        }

    }



}
