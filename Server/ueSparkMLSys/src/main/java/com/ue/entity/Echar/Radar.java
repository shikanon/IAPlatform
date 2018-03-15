package com.ue.entity.Echar;

import com.ue.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明  雷达图基础类
 * @Date: created in 15:07 2017/12/8
 */
public class Radar extends BaseEntity {

    private String name;   //标题

    private List<Element> skillValue =new ArrayList<>();  //雷达图


    private double max;  //所有Element中的max最大值

    public void addElement(String text,Double value ){
        Element element=new Element(text,value);
        this.skillValue.add(element);
    }


    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Radar(String name, List<Element> skillValue) {
        this.name = name;
        this.skillValue = skillValue;
    }

    public Radar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getSkillValue() {
        return skillValue;
    }

    public void setSkillValue(List<Element> skillValue) {
        this.skillValue = skillValue;
    }





     /**
       *@auther: 作者 sigma
       *@description: 类说明  雷达图元素基础类
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 17:06 2017/12/8
       */
    private class Element{
       private  String text;  //中文说明   如：精确度、F1值、召回率等

       private  Double value; //值所对应的大小

       private  String  max;  //最大值


         public Element(String text, Double value, String max) {
             this.text = text;
             this.value = value;
             this.max = max;
         }

         public Element(String text, Double value) {
             this.text = text;
             this.value = value;
         }

         public String getText() {
             return text;
         }

         public void setText(String text) {
             this.text = text;
         }

         public Double getValue() {
             return value;
         }

         public void setValue(Double value) {
             this.value = value;
         }

         public String getMax() {
             return max;
         }

         public void setMax(String max) {
             this.max = max;
         }
     }


}
