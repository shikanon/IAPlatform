package com.ue.test;

import com.ue.entity.graph.OozieDatasetNode;
import com.ue.entity.graph.OozieEdge;
import com.ue.entity.graph.OozieGraph;
import com.ue.entity.graph.OozieProgramNode;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 9:52 2017/11/24
 */
public class test {



    public static void main(String[] args) throws Exception {

//        String xml=readString3("grap.txt");
//-
//        OozieGraph oozieGraph=  parse(xml);
//
//        WFBuildServiceImpl wfBuilder = new WFBuildServiceImpl();
//        wfBuilder.buildFromOozieGraph(oozieGraph);
//        String workflow = wfBuilder.asWFGraph().toWorkflow("test");
//

    }



    /**
     * @param xml xml description
     * @throws DocumentException
     */
    public static OozieGraph parse(String xml) throws DocumentException {
        OozieGraph graph = new OozieGraph();
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        // parse widgets
        List<Element> nodes = root.elements("widget");
        for( Element node: nodes){
            String type = node.attributeValue("type");
            if (type.equals("dataset")) {
                OozieDatasetNode odn = parseDatasetNode(node);
                graph.addDatasetNode(odn);
            } else if(type.equals("program")){
                OozieProgramNode opn = parseProgramNode(node);
                graph.addProgramNode(opn);
                graph.addActiveNode(opn.getId());
            }

        }

        // parse edges
        List<Element> enodes = root.elements("edge");
        for(Element elem: enodes){
            OozieEdge edge = parseOozieEdge( elem);
            if (edge != null)
                graph.addEdge(edge);
        }

        return graph;
    }


    /** parse a program node from xml */
    private static OozieProgramNode parseProgramNode(Element xml_node) {
        List<Element> childNodes = xml_node.elements();
        OozieProgramNode node = new OozieProgramNode();

        for( Element child : childNodes){
            String value = child.getText();
            String name = child.getName();

            if ("id".equals(name))
                node.setId(value);
            else if ("moduleId".equals(name))
                node.setModuleId(value);
            else if ( "oozJob".equals(name))
                node.setOozJobId( value );
            else if ("x".equals(name))
                node.setX(value);
            else if ("y".equals(name))
                node.setY(value);
            else if ("file".equals(name))
                node.addFile(value);
            else if( "input_aliases".equals( name ) ){
                node.addInputAliases( value );
            }else if("output_aliases".equals(name)){
                node.addOutputAliases( value );
            }else if ("param".equals(name))
                node.addParam(value);
            else if( "script".equals(name))
                node.setScript(value);
            else if( "incount".equals(name))
                node.setInputFileCount( Integer.parseInt(value));
            else if( "outcount".equals(name))
                node.setOutputFileCount( Integer.parseInt(value));
            else if("is_distributed".equals(name))
                node.setDistributed(Boolean.valueOf(value));
            else if("is_standalone_script".equals(name)){
                node.setStandaloneScript(Boolean.valueOf(value));
            }else if("work_path".equals(name)){
                node.setWorkPath(value);
            }
            else if("cmd_line".equals(name)){
                node.setCmdLine(value);
            }
        }

        return node;
    }



    /** parse a dataset node from xml */
    private static OozieDatasetNode parseDatasetNode(Element xml_node) {
        List<Element> childNodes = xml_node.elements();
        OozieDatasetNode node = new OozieDatasetNode();

        for( Element child : childNodes){
            String value = child.getText();
            String name = child.getName();

            if ("id".equals(name))
                node.setId(value);
            else if ("moduleId".equals(name))
                node.setModuleId(value);
            else if ("x".equals(name))
                node.setX( value);
            else if ("y".equals(name))
                node.setY( value);
            else if ("file".equals(name))
                node.setFile(value);
        }

        return node;
    }




    /** Parse a edge from xml
     * @param enode  edge node
     */
    private static OozieEdge parseOozieEdge(Element enode) {

        String src = "";
        String dst = "";
        List<Element> children = enode.elements();
        for( Element child: children){
            if ("source".equals(child.getName())) {
                src = child.getText();
            } else if ("destination".equals(child.getName())) {
                dst = child.getText();
                break;
            }
        }
        if (src.isEmpty() || dst.isEmpty()) {

            return null;
        }
        else{
            OozieEdge edge = new OozieEdge();
            edge.init(src, dst);
            return edge;
        }
    }

    private static String readString3(String path)

    {

        String str="";

        File file=new File(path);

        try {

            FileInputStream in=new FileInputStream(file);

            // size  为字串的长度 ，这里一次性读完

            int size=in.available();

            byte[] buffer=new byte[size];

            in.read(buffer);

            in.close();

            str=new String(buffer,"GB2312");

        } catch (IOException e) {

            // TODO Auto-generated catch block


        }

        return str;

    }



}
