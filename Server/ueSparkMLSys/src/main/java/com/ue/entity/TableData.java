package com.ue.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明  用于存储一个列数据的对象
 * @Date: created in 16:41 2017/12/22
 */
public class TableData {

    private List<String> head; //用于存储表头

    private List<List<String>>  body; //用于存储数据主体

    private Map<String,List<String>> bodys=new HashMap<>(); //用于存储数据主体。 与body类似，存储结构不同

    private  int  Datasize; //用于存放数据的大小、行数

 /**
   *@auther: 作者 sigma
   *@description: 类说明  在bodys中的某一列数据中添加一组数据
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 10:56 2017/12/26
   */
    public  void addbodys(String colname,List<String> values){

        if (bodys.containsKey(colname)){
            bodys.get(colname).addAll(values);

        }else {

            bodys.put(colname,values);
        }


    }


     /**
       *@auther: 作者 sigma
       *@description: 类说明  在bodys中添加一个数据
       *@param: 参数说明   colname数据所属的列名称   value： 数据的值
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 10:49 2017/12/26
       */
    public  void addbodys(String colname,String value){

        if (bodys.containsKey(colname)){
            bodys.get(colname).add(value);

        }else {
            List<String> values=new ArrayList<>();
            values.add(value);
            bodys.put(colname,values);
        }


    }


     /**
       *@auther: 作者 sigma
       *@description: 类说明  用于存储一行的数据
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 16:46 2017/12/22
       */
    public  void  addBody(List<String> line){

        List<List<String>>  body=getBody();



        List<String> head=getHead();

        //size必须相等，且顺序严格不变。
       int  bodysize= body.size();
        int linesize=line.size();
        int headsize=head.size();

        for (int i=0;i<linesize;i++){
            body.get(i).add(line.get(i));

        }

    }

     /**
       *@auther: 作者 sigma
       *@description: 类说明  初始化Body。
       *@param: 参数说明   headsize:列数     linesize： 数据的行数。
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 9:42 2017/12/25
       */
    public  void  initBody(int  headsize){

        this.body= new ArrayList<>(headsize);
        for (int i=0;i<headsize;i++)
        {
            ArrayList<String> temp=new ArrayList<>();
            this.body.add(temp);
        }


    }

    public int getDatasize() {
        return Datasize;
    }

    public void setDatasize(int datasize) {
        Datasize = datasize;
    }

    public Map<String, List<String>> getBodys() {
        return bodys;
    }

    public void setBodys(Map<String, List<String>> bodys) {
        this.bodys = bodys;
    }

    public List<String> getHead() {
        return head;
    }

    public void setHead(List<String> head) {
        this.head = head;
    }

    public List<List<String>> getBody() {
        return body;
    }

    public void setBody(List<List<String>> body) {
        this.body = body;
    }
}
