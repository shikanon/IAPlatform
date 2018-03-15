package com.ue.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 11:36 2017/12/21
 */
public class TokenUtil {



    static JDBCUtil db1 = null;
    static ResultSet ret = null;
    public static void main(String[] args) throws SQLException {

        containToken("99e6b492c26f11e69df55254006ab3f9");

    }
    public  static boolean  containToken(String  token) throws SQLException {

       String sql = "select * from api_v1_token where token='$TOKEN'";//SQL语句

       sql= sql.replace("$TOKEN",token);

        db1 = new JDBCUtil(sql);//创建DBHelper对象


        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集

            if (ret.next()) return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ret.close();
            db1.close();//关闭连接
        }

        return false;
    }

}
