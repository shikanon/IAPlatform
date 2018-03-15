package com.ue.test;

import com.ue.entity.BdaJob;
import com.ue.entity.Program;

import java.io.File;
import java.util.Map;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 18:16 2018/1/2
 */
public class BatchUpload {


    public static void main(String[] args) throws Exception {

        String filepath = "input/算法表格.xlsx";
        File  file=new File(filepath);
        XlsxUtil excelReader = new XlsxUtil(file);

        File f=new File("input/spark.zip");

        String[] title = excelReader.readExcelTitle();

        Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();

        for (int i=1;i<=map.size();i++)
        {
            Program program=new Program();

            String name= (String) map.get(i).get(0);
            String category= (String) map.get(i).get(1);
            String desc= (String) map.get(i).get(2);
            String cmdline= (String) map.get(i).get(3);

            program.setName(name);
            program.setCategory(category);
            program.setDescription(desc);
            program.setCommandLine(cmdline);
            program.setUpload(f);


        }


    }
}
