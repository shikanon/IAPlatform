import com.ue.action.JobAction;
import com.ue.entity.BdaJob;
import com.ue.entity.Category;
import com.ue.service.JobService;
import com.ue.service.serviceImpl.JobServiceImpl;
import com.ue.util.Constants;
import com.ue.util.OozieUtil;
import org.apache.oozie.client.OozieClientException;
import org.junit.Test;

import java.io.*;

/**
 * @auther: 作者 dzc
 * @description: 类说明 测试类
 * @Date: created in 18:03 2017/11/10
 */
public class MyTest {

    @Test
  public  void  testGetUrl() throws IOException, OozieClientException {
        String jobId="0000008-171107105146910-oozie-root-W";
        String url = OozieUtil.getUrl(jobId) ;
        System.out.println(url);

  }



    //测试kill 一个任务流
    @Test
    public void testKill() throws IOException, OozieClientException {

        String jobId="0000008-171107105146910-oozie-root-W";

        String url = OozieUtil.getUrl(jobId) ;
        System.out.println(url);

        String oozieJobID = "0000009-171107105146910-oozie-root-W";

//        JobService jobService = new JobServiceImpl();
//        Constants s = new Constants();
//        JobAction jobAction = new JobAction(jobService);
//
//        jobAction.killByOozieJobID(oozieJobID);


    }


    //测试提交任务流
    @Test
    public void testSbmit() throws IOException {
        //读取workflow文件，


        System.out.println(Constants.NAME_NODE);

        String path = "E:\\优异科技\\EasyML整理\\workflownew.txt";
        String workflow = readWorkFlow(path);

        JobService jobService = new JobServiceImpl();
        Constants s = new Constants();
        JobAction jobAction = new JobAction(jobService);
        BdaJob result = jobAction.submit("sigmaTest", "1111111111111111", workflow, "sigma_test", "林钢鑫测试");

        System.out.println("ID:" + result.getOozieJob());


    }


    @Test
    public void testDivide() {

        Category category = new Category();
        System.out.println(Category.class.toString());

        String str1 = "System Programs>分类";
        String str2 = "System Program";
        String[] s = str1.split(">");
        if (s.length > 0) {
            if (str2.equals(s[0])) {
                System.out.println("存在");
            } else {
                System.out.println("不存在");
            }
        }
        if (str1.indexOf(str2) != -1) {
            System.out.println("ok");
        } else {
            System.out.println("no");
        }

        System.out.println("holle test");
    }


    public static String readWorkFlow(String path) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, path);
        return sb.toString();
    }

    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }
}
