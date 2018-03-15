package com.ue.util.XML.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 14:55 2018/3/2
 */

@XmlRootElement(name = "config")
public class Config {


    private  String name;

    private  String desc;


    private  String modeltype;

    private  String mainclass;

    private  String pagename;


    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    @XmlElement(name = "modeltype")
    public String getModeltype() {
        return modeltype;
    }

    public void setModeltype(String modeltype) {
        this.modeltype = modeltype;
    }

    @XmlElement(name = "mainclass")
    public String getMainclass() {
        return mainclass;
    }

    public void setMainclass(String mainclass) {
        this.mainclass = mainclass;
    }
    @XmlElement(name = "pagename")
    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }
}
