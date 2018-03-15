package com.ue.dataAnalyze;

import com.ue.entity.TableData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 15:22 2018/1/2
 */
public class TableThread implements Runnable {


    static int ThreadNum = 0;
    private TableData globalDatas;   //Total data
    private List<String> localDatas;  //Every thread process data

    private  List<String> heads; //用于存 数据表头名称

    private int ThreadCurrent;  // Current thread id


    public TableThread(TableData globalDatas, List<String> localDatas,List<String> heads) {
        this.globalDatas = globalDatas;
        this.localDatas = localDatas;
        this.heads=heads;
        ThreadCurrent = ++ThreadNum;;
    }

    @Override
    public void run() {

        Map<String,List<String>> data=new HashMap<>();

        for (int i = 0; i < localDatas.size(); i++) {
            String[] bodys = localDatas.get(i).split("\\s+|\\,|\\|");

            for (int j=0;j<bodys.length;j++)
            {
                String key=this.heads.get(j);
                String value=bodys[j];
                if (data.containsKey(key)){
                    data.get(key).add(value);
                }else {
                    List<String> values=new ArrayList<>();
                    values.add(value);
                    data.put(key,values);
                }

            }

        }

        ThreadNum--;
        synchronized(globalDatas){

            for (Map.Entry<String, List<String>> entry : data.entrySet())
            {
                String column=entry.getKey();
                List<String> values=entry.getValue();
                globalDatas.addbodys(column, values);
            }

        }
    }
}
