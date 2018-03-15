//package com.ue.action;
//
//import com.opensymphony.xwork2.ModelDriven;
//import com.ue.service.WFBuildService;
//import org.apache.log4j.Logger;
//import org.apache.struts2.convention.annotation.Action;
//import org.apache.struts2.convention.annotation.Namespace;
//import org.dom4j.DocumentException;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @auther: 作者 sigma
// * @description: 类说明
// * @Date: created in 15:52 2017/11/24
// */
//@Namespace("/")
//@Action(value = "wfBuildAction")
//public class WFBuildAction extends  BaseAction  {
//
//    private static final Logger logger = Logger.getLogger(UserAction.class);
//
//    private String graphxml;
//
//    private WFBuildService wfBuildService;
//
//    public WFBuildService getWfBuildService() {
//        return wfBuildService;
//    }
//    @Autowired
//    public void setWfBuildService(WFBuildService wfBuildService) {
//        this.wfBuildService = wfBuildService;
//    }
//
//    public String getGraphxml() {
//        return graphxml;
//    }
//
//    public void setGraphxml(String graphxml) {
//        this.graphxml = graphxml;
//    }
//
//
////    public void getWFXML(){
////
////        try {
////          String workflow=   wfBuildService.buildByGraphxml(getGraphxml());
////
////           System.out.println(workflow);
////
////            super.writeJson(workflow);
////        } catch (DocumentException e) {
////            e.printStackTrace();
////        }
////
////
////    }
//
//
//
//}
