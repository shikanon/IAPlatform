package com.ue.util.XML.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 14:57 2018/3/2
 */

@XmlRootElement(name = "parameters")
public class Parameters {



    private  Parameter parameters;

    @XmlElement(name = "parameters")
    public Parameter getParameters() {
        return parameters;
    }

    public void setParameters(Parameter parameters) {
        this.parameters = parameters;
    }
}
