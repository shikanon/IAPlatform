package com.ue.service.serviceImpl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ue.dataAnalyze.*;
import com.ue.entity.Constant;
import com.ue.entity.Echar.BarChart;
import com.ue.entity.Echar.Table;
import com.ue.entity.OozieJob;
import com.ue.entity.TableData;
import com.ue.service.OozieJobService;
import com.ue.service.PresentService;
import com.ue.util.Constants;
import com.ue.util.HDFSIO;
import com.ue.util.share.util.DataTools;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 16:32 2017/12/22
 * <p>
 * path: ${appPath}/DecisionTreeClass_train-a2189837f4d9-0/y22yu8F93yu51FAyu6A21yu578By22y7Dy20
 */
@Service("presentService")
public class PresentServiceImpl implements PresentService {

    //定义日志输出实体
    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    private OozieJobService oozieJobService;

    public OozieJobService getOozieJobService() {
        return oozieJobService;
    }

    @Autowired
    public void setOozieJobService(OozieJobService oozieJobService) {
        this.oozieJobService = oozieJobService;
    }

    public static void main(String[] args) throws Exception {

        // List<String> res= HDFSIO.readAllData("hdfs://hadoop-master:9000///EML/Data/6d1c01df-c87f-44fc-acb4-577056df3ca5",true);

        //  String path = "/EML/Data/bee47964-bc78-483e-85eb-be82c41fc6c8";

        //  String path="/EML/oozie/APP-PATH-31694652-055e-4e06-b39c-6af59194f421/Prediction-2FF92E36EC75-0/y22yu9884yu6D4Byu7ED3yu679Cy22y7Dy20";

        String path = "/EML/Data/0791713f-6d74-4ec0-82d6-e3b088d1910a";
        PresentServiceImpl ll = new PresentServiceImpl();

        ll.previewDada(path, "");
        //  DataDescribe result = ll.getDataDescribe(path,"0000036-171208070629007-oozie-root-W");

        //System.out.println("统计结果" + result);

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  获取数据的折线图、柱状图对象
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 11:09 2017/12/29
     */
    public BarChart getBarChar() {


        return null;
    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明  通过读取以及统计好的数据,返回数据的统计特征。
     * @param: 参数说明  path: ${appPath}/DecisionTreeClass_train-a2189837f4d9-0/y22yu8F93yu51FAyu6A21yu578By22y7Dy20
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 16:45 2017/12/28
     */
    public DataDescribe getDataDescribe(String path, String ooziejobid) throws Exception {


        /** 根据输入进来的path，获取统计指标数据的存放路径 */
        OozieJob ooziejob = oozieJobService.getById(ooziejobid);
        String appPath = ooziejob.getPath();
        path = path.replace("${appPath}", appPath);//存放统计结果的路径

        path = path.replace("/EML", Constants.SAMPLE_PATH);

        List<String> data = HDFSIO.readAllData(path, true);

        DataDescribe result = new DataDescribe();
        //解析数据
        List<String[]> table = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {

            String[] line = data.get(i).split(",");
            table.add(line);
        }

        String[] headers = table.get(0);

        result.addHeads(headers);

        for (int i = 1; i < table.size(); i++) {
            String feature = table.get(i)[0];

            for (int j = 1; j < headers.length; j++) {

                String value = table.get(i)[j];

                String head = headers[j];
                result.getDescribe().get(head).put(feature, value);
            }
        }

        return result;

    }


    /**
     * @param path
     * @param ooziejobid
     * @auther: 作者 sigma
     * @description: 类说明 将路径下的数据封装成折现图的数据格式。
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:57 2018/1/2
     */
    @Override
    public BarChart drowLineChar(String path, String ooziejobid) throws Exception {


        TableData tableData = readData(path, ooziejobid);

        BarChart result = new BarChart();

        List<String> heads = tableData.getHead();
        Map<String, List<String>> bodys = tableData.getBodys();

        for (int i = 0; i < heads.size(); i++) {
            String key = heads.get(i);
            List<String> value = bodys.get(key);
            result.addElement(key, value);
        }

        return result;

    }

    /**
     * @param path
     * @auther: 作者 sigma
     * @description: 类说明  数据预览，直接封装path的数据，并返回给前端
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 10:40 2018/1/2
     */
    @Override
    public JsonArray previewDada(String path, String ooziejobid) throws Exception {

        TableData tableData = readData(path, ooziejobid);

        JsonArray jsonArray = new JsonArray();

        int size = tableData.getDatasize();

        List<String> heads = tableData.getHead();

        Map<String, List<String>> bodys = tableData.getBodys();

        for (int i = 0; i < size; i++) {
            JsonObject line = new JsonObject();


            for (int j = 0; j < heads.size(); j++) {
                String key = heads.get(j);
                String value = bodys.get(key).get(i);

                line.addProperty(key, value);

            }

            jsonArray.add(line);
        }

        return jsonArray;


//        OozieJob ooziejob = oozieJobService.getById(ooziejobid);
//        String appPath = ooziejob.getPath();
//        path = path.replace("${appPath}", appPath);//存放统计结果的路径
//
//
//        long start = System.currentTimeMillis();
//        List<String> data = HDFSIO.readAllData(path, true);
//
//
//        long readdatatime = System.currentTimeMillis() - start;
//        logger.info("Read data time = " + String.valueOf(System.currentTimeMillis() - start));
//
//        Table tabledata = new Table();
//
//        //初始化表格头部
//        String[] heads = data.get(0).split("\\s+|\\,|\\|");
//        List<String> head = Arrays.asList(heads);
//        tabledata.setHeads(head);
//
//        data = data.subList(1, data.size());
//        for (int i = 0; i < data.size(); i++) {
//            String[] line = data.get(i).split("\\s+|\\,|\\|");
//            tabledata.addLine(line);
//        }
//
//        return tabledata.getArray();


    }

    /**
     * @param path
     * @auther: 作者 sigma
     * @description: 类说明  获取数据的某一列的统计指标
     * @param: 参数说明  path 数据的路径。 colname：需要统计的列名称  datatype：数据的类型。 暂时仅支持csv文件。
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 14:36 2017/12/6
     */
    @Override
    public DataFeature extDataFeature(String path, String colname, String datatype) throws IOException {


        long start = System.currentTimeMillis();
        List<String> data = HDFSIO.readAllData(path, true);


        //计算读取数据的时间
        long readdatatime = System.currentTimeMillis() - start;
        logger.info("Read data time = " + String.valueOf(System.currentTimeMillis() - start));


        // 考虑到数据太多，使用多线程来构建TableData 对象。

        //去除头部。 也就是表头。
        TableData tableData = new TableData();  //用于存储读取的数据对象
        String[] heads = data.get(0).split("\\s+|\\,|\\|");
        List<String> head = Arrays.asList(heads);
        tableData.setHead(head);

        data = data.subList(1, data.size());


        start = System.currentTimeMillis();
        //构造线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        double threadNum = ThreadUtils.THREAD_NUM;
        int count = (int) Math.ceil(data.size() / threadNum);

        //每次启动100个线程来计算数据。 每个线程计算的量不同。
        //如果数据量小于线程的量，则只需要起一个线程来跑就可以了。
        if (data.size() < threadNum) {
            int startInx = 0;
            int endInx = data.size();
            exec.execute(new ThreadDataParser(tableData, data.subList(startInx, endInx), colname, head, datatype));
        } else {
            for (int i = 0; i < threadNum; i++) {

                int startInx = i * count;
                int endInx = startInx + count;
                if (i == threadNum - 1)
                    endInx = data.size();
                exec.execute(new ThreadDataParser(tableData, data.subList(startInx, endInx), colname, head, datatype));

            }
        }

        exec.shutdown();
        // wait until sum-process is over
        try {
            exec.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            logger.info("exec.awaitTermination(5, TimeUnit.MINUTES) occurs error : " + e);
        }
        //计算解析数据的时间
        long ParseDatatime = System.currentTimeMillis() - start;
        logger.info("Parse data column size = " + data.size() + "; time = " + String.valueOf(System.currentTimeMillis() - start));

        System.out.println();

        //通过多线程来构建tableData对象。 以下为计算数据的统计指标

        // 判断是否为字符串，如果为字符串则没有标准差、没有均值等。 只有缺失值、不缺失的值。
        start = System.currentTimeMillis();
        String verifyTypeData = null;
        List<String> datas = tableData.getBodys().get(colname);
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i) == null || datas.get(i).trim().equals(""))
                continue;
            else {
                verifyTypeData = datas.get(i);
                break;
            }
        }

        boolean numeric = DataTools.isNumeric(verifyTypeData);

        //用于存放数据的统计指标
        DataFeature dataFeature = new DataFeature();
        int missingNum = 0;
        Set<String> uniqueDatas = new HashSet<String>();
        if (!numeric)  //如果为字符串，则计算不重复的数量以及缺失值
        {
            for (String d : datas) {
                if (d == null || "".equals(d.trim()))
                    missingNum = missingNum + 1;
                else
                    uniqueDatas.add(d);
            }
            dataFeature = new DataFeature(uniqueDatas.size(), missingNum, numeric);
        } else {
            double sum = 0;
            List<Double> effictDatas = new ArrayList<Double>();
            for (String d : datas) {
                if (d == null || "".equals(d.trim()))
                    missingNum = missingNum + 1;
                else {
                    uniqueDatas.add(d);
                    effictDatas.add(Double.valueOf(d));
                    sum = sum + Double.valueOf(d);
                }
            }
            int effSize = effictDatas.size();
            Collections.sort(effictDatas);

            double mean = sum / effSize;
            double min = effictDatas.get(0);
            double max = effictDatas.get(effSize - 1);

            double s = 0;
            for (double d : effictDatas) {
                s = s + (d - mean) * (d - mean);
            }
            double standDeviation = (s / effSize);

            double median = effictDatas.get(0);
            if (effictDatas.size() % 2 == 0) {
                double left = effictDatas.get(effSize / 2 - 1);
                double right = effictDatas.get(effSize / 2);
                median = (left + right) / 2;
            } else
                median = effictDatas.get((effSize + 1) / 2);
            dataFeature = new DataFeature(m2(mean), median, min, max, m2(standDeviation), uniqueDatas.size(), missingNum, numeric);

        }

        long statisicDatatime = System.currentTimeMillis() - start;
        dataFeature.setStatisicDatatime(statisicDatatime);
        dataFeature.setParseDatatime(ParseDatatime);
        dataFeature.setReadDatatime(readdatatime);

        logger.info("Unique data = " + uniqueDatas.size() + "Missing num=" + missingNum + "Numberic=" + numeric);
        logger.info("Feature parse time = " + String.valueOf(System.currentTimeMillis() - start));
        return dataFeature;

    }


    /**
     * @auther: 作者 sigma
     * @description: 类说明 私有方法，通过路径、以及ooziejobID,使用多线程将数据分装成TableData格式
     * @param: 参数说明
     * @result： 结果说明
     * @exceptions： 异常说明
     * @Date: created in 15:07 2018/1/2
     */
    private TableData readData(String path, String ooziejobid) throws Exception {




        if (ooziejobid!=null && (!ooziejobid.equals("")) ) {
            OozieJob ooziejob = oozieJobService.getById(ooziejobid);
            String appPath = ooziejob.getPath();
            path = path.replace("${appPath}", appPath);//存放统计结果的路径
        }


        long start = System.currentTimeMillis();
        List<String> data = HDFSIO.readAllData(path, true);


        //计算读取数据的时间
        long readdatatime = System.currentTimeMillis() - start;
        logger.info("Read data time = " + String.valueOf(System.currentTimeMillis() - start));


        // 考虑到数据太多，使用多线程来构建TableData 对象。

        //去除头部。 也就是表头。
        TableData tableData = new TableData();  //用于存储读取的数据对象
        String[] heads = data.get(0).split("\\s+|\\,|\\|");
        List<String> head = Arrays.asList(heads);
        tableData.setHead(head);

        data = data.subList(1, data.size());

        tableData.setDatasize(data.size());


        start = System.currentTimeMillis();
        //构造线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        double threadNum = ThreadUtils.THREAD_NUM;
        int count = (int) Math.ceil(data.size() / threadNum);

        //每次启动100个线程来计算数据。 每个线程计算的量不同。
        //如果数据量小于线程的量，则只需要起一个线程来跑就可以了。
        if (data.size() < threadNum) {
            int startInx = 0;
            int endInx = data.size();
            exec.execute(new TableThread(tableData, data.subList(startInx, endInx), head));
        } else {
            for (int i = 0; i < threadNum; i++) {

                int startInx = i * count;
                int endInx = startInx + count;

                System.out.println("i=" + i);
                if (i == threadNum - 1)
                    endInx = data.size();
                exec.execute(new TableThread(tableData, data.subList(startInx, endInx), head));
            }
        }

        exec.shutdown();
        // wait until sum-process is over
        try {
            exec.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            logger.info("exec.awaitTermination(5, TimeUnit.MINUTES) occurs error : " + e);
        }


        return tableData;
    }


    /**
     * Keep two digit number
     * 数据保留两位数
     *
     * @param value
     * @return
     */
    private double m2(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(value));
    }
}
