package com.ue.entity.Echar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明  用于封装折线图、柱状图的基础类
 * @Date: created in 17:57 2017/12/28
 */
public class BarChart {




    private  List<Element> data=new ArrayList<>();


    public void addElement(String name,List value){
        Element e=new Element();
        e.setName(name);
        e.setSkillValue(value);
        data.add(e);
    }


    public List<Element> getData() {
        return data;
    }

    public void setData(List<Element> data) {
        this.data = data;
    }

    private class Element{
       private  String name;

       private List skillValue;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public List getSkillValue() {
           return skillValue;
       }

       public void setSkillValue(List skillValue) {
           this.skillValue = skillValue;
       }
   }



}
