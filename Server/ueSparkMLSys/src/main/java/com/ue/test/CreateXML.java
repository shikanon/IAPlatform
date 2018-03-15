package com.ue.test;

import com.ue.entity.Program;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 16:01 2018/3/1
 */
public class CreateXML {




    public  static  void run(String[] args) throws ParserConfigurationException, TransformerException {

        File f=new File("input/model.xml");


        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.newDocument();
        document.setXmlStandalone(true);
        Element root=document.createElement("tb_student");
        for (int i = 0; i < 3; i++) {
            Element student=document.createElement("student");
            Element name=document.createElement("name"),
                    age=document.createElement("age"),
                    grade=document.createElement("grade");
            student.setAttribute("id", i+"");
            name.setTextContent("张"+i);
            age.setTextContent(""+i*5);
            grade.setTextContent(""+i*20);
            student.appendChild(name);
            student.appendChild(age);
            student.appendChild(grade);
            root.appendChild(student);
        }
        document.appendChild(root);
        TransformerFactory tff=TransformerFactory.newInstance();
        Transformer tf=tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");

        writeXML("input/test11.xml",document);

        //tf.transform(new DOMSource(document), new StreamResult(f));
    }


     /**
       *@auther: 作者 sigma
       *@description: 类说明  读取xmls文件，生成xml文件
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 16:01 2018/3/1
       */
    public  static  void main(String[]args) throws Exception {

        String filepath = "input/算法表格.xlsx";
        File file=new File(filepath);
        XlsxUtil excelReader = new XlsxUtil(file);

        File f=new File("input/spark.zip");

        String[] title = excelReader.readExcelTitle();

        Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();



        DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.newDocument();
        document.setXmlStandalone(true);
        Element root=document.createElement("config");




        for (int i=1;i<=map.size();i++)
        {
            Program program=new Program();

            String modelname= (String) map.get(i).get(0);
            String modelcategory= (String) map.get(i).get(2);
            String modeldesc= (String) map.get(i).get(3);
            String modelcmdline= (String) map.get(i).get(4);

            //解析CMDline，从cmd中解析出  主类位置，jar包名称  参数的变量名等

            AnalysisResult result=  new CreateXML().analysisCMD(modelcmdline);



            Element element=document.createElement("element");

            Element name=document.createElement("name"),   //<!-- 算法名称-->
                    desc=document.createElement("desc"),  //算法简要说明
                    modeltype=document.createElement("modeltype"),   //算法可选类型：分类，聚类、回归、模型评估
                    mainclass=document.createElement("mainclass"),  //算法主类位置
                    pagename=document.createElement("pagename"),  //程序jar包名称
                    parameters=document.createElement("parameters"); //该算法的参数列表


            name.setTextContent(modelname);
            desc.setTextContent(modeldesc);
            modeltype.setTextContent(modelcategory);
            mainclass.setTextContent(result.getMainclass());
            pagename.setTextContent(result.getPageName());

            List<parameter> resultparameters=result.getParameters();

            for (int k=0;k<resultparameters.size();k++)
            {
                parameter pk=resultparameters.get(k);

                Element  parameter=document.createElement("parameter"),  //参数列表中的参数
                        type=document.createElement("type"),   //参数类型
                        par=document.createElement("par"),   //参数在算法中的变量名
                        pardesc=document.createElement("pardesc"),//参数说明
                        defaul=document.createElement("default");


                type.setTextContent(pk.getType());
                par.setTextContent(pk.getPar());
                pardesc.setTextContent(pk.getPardesc());
                defaul.setTextContent(pk.getDefau());

                parameter.appendChild(type);
                parameter.appendChild(par);
                parameter.appendChild(pardesc);
                parameter.appendChild(defaul);

                parameters.appendChild(parameter);
            }


            element.appendChild(name);
            element.appendChild(desc);
            element.appendChild(modeltype);
            element.appendChild(mainclass);
            element.appendChild(pagename);
            element.appendChild(parameters);

            root.appendChild(element);


        }


        document.appendChild(root);

        writeXML("input/test22.xml",document);


    }

     /**
       *@auther: 作者 sigma
       *@description: 类说明  解析CMD数据
       *@param: 参数说明
       *@result： 结果说明
       *@exceptions： 异常说明
       *@Date: created in 16:32 2018/3/1
       */
    private  AnalysisResult analysisCMD(String modelcmdline) {


        AnalysisResult result=new AnalysisResult();


        List<parameter>  parameters= new ArrayList<>();


        String[] elements=modelcmdline.split("\\s+");

        String mainclass=elements[2];
        String pagename=elements[3];


        for (int i=4;i<elements.length;)
        {

            parameter paramete=new parameter();
            String par=elements[i].replace("--","");
            String temp=elements[i+1];
            temp=temp.substring(1,temp.length()); //去头
            temp=temp.substring(0,temp.length()-1); //去尾
            String[] temps=temp.split(":");
            String type=temps[0];

            String defaul="";

            String pardesc="";


            if (type.equals("in")||type.equals("out"))
            {
                pardesc=temps[2];

            }
            else
            {
                pardesc=type;
                type=temps[1];

                defaul=temps[2].replace("default,","");

            }

            paramete.setType(type);
            paramete.setDefau(defaul);
            paramete.setPar(par);
            paramete.setPardesc(pardesc);

            parameters.add(paramete);

            i=i+2;
        }


        result.setMainclass(mainclass);
        result.setPageName(pagename);
        result.setParameters(parameters);

        return result;


    }


    public static int writeXML(String path, Document doc)
    {
        File markFile = new File(path);
        if (markFile.exists())
        {
            return 1;
        }
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(path);
        }
        catch (FileNotFoundException e)
        {
            //logger.debug("数据上报XML文件输出出错：系统找不到指定路径");
            return -1;
        }
        OutputStreamWriter outwriter;
        try
        {
            outwriter = new OutputStreamWriter(fos, "UTF-8");
            Source sorce = new DOMSource(doc);
            Result result = new StreamResult(outwriter);
            Transformer trans;
            try
            {
                trans = TransformerFactory.newInstance().newTransformer();
                trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                trans.transform(sorce, result);
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return 0;
            }
            catch (TransformerConfigurationException e)
            {
                e.printStackTrace();
                return -1;
            }
            catch (TransformerFactoryConfigurationError e)
            {
                e.printStackTrace();
                return -1;
            }
            catch (TransformerException e)
            {
                e.printStackTrace();
                return -1;
            }
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
            return -1;
        }

    }


    class  AnalysisResult{


        List<parameter> parameters;

        String mainclass;

        String pageName;

        public AnalysisResult() {
        }

        public List<parameter> getParameters() {
            return parameters;
        }

        public void setParameters(List<parameter> parameters) {
            this.parameters = parameters;
        }

        public String getMainclass() {
            return mainclass;
        }

        public void setMainclass(String mainclass) {
            this.mainclass = mainclass;
        }

        public String getPageName() {
            return pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName;
        }
    }




    class  parameter{

       private String type;
      private  String par;
        private  String defau;
        private String pardesc;


        public parameter() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPar() {
            return par;
        }

        public void setPar(String par) {
            this.par = par;
        }

        public String getDefau() {
            return defau;
        }

        public void setDefau(String defau) {
            this.defau = defau;
        }

        public String getPardesc() {
            return pardesc;
        }

        public void setPardesc(String pardesc) {
            this.pardesc = pardesc;
        }
    }







}
