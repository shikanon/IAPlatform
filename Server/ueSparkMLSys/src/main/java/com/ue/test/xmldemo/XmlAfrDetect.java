package com.ue.test.xmldemo;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 15:17 2018/3/2
 */


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "ResponseInfo")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class XmlAfrDetect {
}
