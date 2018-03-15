package com.ue.intereptor;

import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.ue.entity.BaseEntity;
import com.ue.entity.Constant;
import com.ue.entity.ParameterEnum;
import com.ue.entity.ResultJson;
import com.ue.util.TokenUtil;
import net.sf.ehcache.search.impl.BaseResult;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;


/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 16:23 2017/12/20
 */
public class TokenVerifyInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//         Object[] args = invocation.getArguments();
//
//         String  TOKEN= request.getHeader(Constant.TOKEN);
//
//         Enumeration<String> authtype=  request.getHeaderNames();
//
       return invocation.proceed();
//
//        String auth=request.getHeader("authorization");
//
//       String  base64Token= auth.split("\\s+")[1];
//
//       // 将字符串经过base64解密
//        String  token=getFromBase64(base64Token);
//        if ( (!token.equals(null)) && (!token.equals("")) && TokenUtil.containToken(token))
//           return invocation.proceed();
//        else {
//          return JSON.toJSONString(new ResultJson(false, ParameterEnum.requestFail.getDes(),"TOKEN验证不通过"));
//       }


    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  base64 解码
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 9:53 2017/12/26
     */
    private static String getFromBase64(String str) {

        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

}
