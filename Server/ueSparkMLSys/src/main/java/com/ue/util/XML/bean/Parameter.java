package com.ue.util.XML.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 14:58 2018/3/2
 */
@XmlRootElement(name = "parameter")
public class Parameter {


    private  String type;

    private  String par;

    private  String pardesc;

    private  String defaul;

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @XmlElement(name = "par")
    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }
    @XmlElement(name = "pardesc")
    public String getPardesc() {
        return pardesc;
    }

    public void setPardesc(String pardesc) {
        this.pardesc = pardesc;
    }

    @XmlElement(name = "defaul")
    public String getDefaul() {
        return defaul;
    }

    public void setDefaul(String defaul) {
        this.defaul = defaul;
    }
}


