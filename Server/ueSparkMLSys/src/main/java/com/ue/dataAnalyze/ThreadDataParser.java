package com.ue.dataAnalyze;

import com.ue.entity.TableData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明  多线程数据解析器，使用多线程解析大数据集格式
 * @Date: created in 18:07 2017/12/25
 */
public class ThreadDataParser implements Runnable {

    static int ThreadNum = 0;

    private TableData globalDatas;   //Total data
    private List<String> localDatas;  //Every thread process data
    private List<String> columns;  // 数据的所有列名称
    private String column;  // 需要计算的数据列名
    private String dataType; //Data type
    private int ThreadCurrent;  // Current thread id


    public ThreadDataParser(TableData globalDatas, List<String> localDatas, String column, List<String> head, String dataType) {
        this.globalDatas = globalDatas;
        this.localDatas = localDatas;
        this.column = column;
        this.dataType = dataType;
        this.columns = head;
        ThreadCurrent = ++ThreadNum;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {


        List<String> values=new ArrayList<>();

        for (int i = 0; i < localDatas.size(); i++) {
            String[] bodys = localDatas.get(i).split("\\s+|\\,|\\|");
            //需要计算列的数组所属下标
            int index = this.columns.indexOf(column);
            String value = bodys[index];
            values.add(value);
        }
        ThreadNum--;
        synchronized(globalDatas){
            globalDatas.addbodys(column, values);
        }
    }

}

