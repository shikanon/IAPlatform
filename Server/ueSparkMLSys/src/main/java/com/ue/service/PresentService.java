package com.ue.service;

import com.google.gson.JsonArray;
import com.ue.dataAnalyze.DataDescribe;
import com.ue.dataAnalyze.DataFeature;
import com.ue.entity.Echar.BarChart;
import com.ue.entity.Echar.Table;
import com.ue.entity.TableData;

import java.io.IOException;
import java.util.List;

/**
 * @auther: 作者 sigma
 * @description: 类说明   展示接口。
 * @Date: created in 16:15 2017/12/5
 */
public interface PresentService {



 /**
   *@auther: 作者 sigma
   *@description: 类说明 将路径下的数据封装成折现图的数据格式。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 14:57 2018/1/2
   */
 public BarChart drowLineChar(String path, String ooziejobid) throws Exception;





 /**
   *@auther: 作者 sigma
   *@description: 类说明  数据预览，直接封装path的数据，并返回给前端
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 10:40 2018/1/2
   */
 public JsonArray previewDada(String path,String ooziejobid) throws IOException, Exception;




 /**
   *@auther: 作者 sigma
   *@description: 类说明   通过java多线程，计算数据的统计特征。
   *@param: 参数说明
   *@result： 结果说明
   *@exceptions： 异常说明
   *@Date: created in 14:36 2017/12/6
   */
 public DataFeature extDataFeature(String path, String colname, String datatype) throws IOException;


 /**
  * @auther: 作者 sigma
  * @description: 类说明  通过读取以及统计好的数据,返回数据的统计特征。
  * @param: 参数说明  path: ${appPath}/DecisionTreeClass_train-a2189837f4d9-0/y22yu8F93yu51FAyu6A21yu578By22y7Dy20
  * @result： 结果说明
  * @exceptions： 异常说明
  * @Date: created in 16:45 2017/12/28
  */
 public DataDescribe getDataDescribe(String path,String ooziejobid) throws Exception;


}
