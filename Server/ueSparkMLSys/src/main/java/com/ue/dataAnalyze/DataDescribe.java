package com.ue.dataAnalyze;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明  用于封装 dataframe.describe() 所计算的结果
 * @Date: created in 16:49 2017/12/28
 */
public class DataDescribe {



    Map<String,Map<String,String>> describe=new HashMap<>();





     /**
       *@auther: 作者 sigma
       *@description: 类说明  添加表头。并初始化
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 16:55 2017/12/28
       */
    public  void addHeads(String[] head){
        for (String s:head){
            this.describe.put(s,new HashMap<>());
        }

    }


    public Map<String, Map<String, String>> getDescribe() {
        return describe;
    }

    public void setDescribe(Map<String, Map<String, String>> describe) {
        this.describe = describe;
    }
}
