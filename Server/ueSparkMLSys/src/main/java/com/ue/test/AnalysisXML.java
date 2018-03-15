package com.ue.test;

import com.ue.entity.Program;
import com.ue.util.XML.bean.Config;
import org.apache.hadoop.hdfs.DFSClient;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;



/**
 * @auther: 作者 sigma
 * @description: 类说明  对算法的xml文件进行解析
 * @Date: created in 17:54 2018/3/1
 */
public class AnalysisXML {



    public  static  void main(String[]args) throws ParserConfigurationException, IOException, SAXException {








        try {
            File file = new File("input/test22.xml");

            String ss=new AnalysisXML().fileRead(file);


            StringReader xml = new StringReader(ss);
            Config responseInfo =JAXB.unmarshal(xml, Config.class);



            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Config a = (Config) jaxbUnmarshaller.unmarshal(file);
            System.out.println(a.toString());

        }catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


//
//        /**
//         * 用于存放解析的算法信息
//         */
//        List<Program> programList=new ArrayList<>();
//
//
//
//
//        //创建一个DocumentBuilderFactory的对象
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        //创建一个DocumentBuilder的对象
//        //创建DocumentBuilder对象
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
//        Document document = db.parse("input/test22.xml");
//        //获取所有element节点的集合
//        NodeList elementList = document.getElementsByTagName("element");
//
//
//        //遍历每一个element节点
//
//        int jjj=elementList.getLength();
//
//
//        for (int i=0;i<elementList.getLength();i++)
//        {
//
//            //用于存放当前节点的解析信息。
//            Program program=new Program();
//
//
//            //通过 item(i)方法 获取一个element节点，nodelist的索引值从0开始
//            Node element = elementList.item(i);
//
//            //解析element节点的子节点
//            NodeList childNodes = element.getChildNodes();
//
//            int childNodeslength=childNodes.getLength();
//
//
//            for (int j = 0; j < childNodes.getLength(); j++) {
//                //通过item(index)方法获取book节点的某一个属性
//                Node attr = childNodes.item(j);
//
//                String sss=attr.getTextContent();
//
//                //获取属性名
//                System.out.print("属性名：" + attr.getNodeName());
//                //获取属性值
//                System.out.println("--属性值" + attr.getNodeValue());
//            }
//
//
//
//
//
//
//            programList.add(program);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        }







    }

    public String fileRead(File file) throws Exception {

        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();

        return str;
    }



}
