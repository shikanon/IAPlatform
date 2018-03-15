package com.ue.entity.Echar;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 11:01 2018/1/2
 */
public class Table {

    //private List<JsonObject> data;

   private   List<String> heads;

   private List<String> data; //弃用。

   private JsonArray array=new JsonArray();
      /**
        *@auther: 作者 sigma
        *@description: 类说明 添加一行数据
        *@param: 参数说明
        *@result： 结果说明
        *@exceptions： 异常说明
        *@Date: created in 11:07 2018/1/2
        */
      public boolean addLine(String[] line){

          JsonObject json=new JsonObject();
          if (line.length!=heads.size())  return false;

          for (int i=0;i<line.length;i++){

             String value= line[i];
              String key=heads.get(i);

             json.addProperty(key,value);

          }

          array.add(json);
         // data.add( json.toString());

          return true;


      }


    public JsonArray getArray() {
        return array;
    }

    public void setArray(JsonArray array) {
        this.array = array;
    }

    public Table() {


        this.heads=new ArrayList<>();


    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<String> getHeads() {
        return heads;
    }

    public void setHeads(List<String> heads) {
        this.heads = heads;
    }
}
