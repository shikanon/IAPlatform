package com.ue.util;

import java.sql.*;

/**
 * @auther: 作者 sigma
 * @description: 类说明
 * @Date: created in 15:38 2017/12/20
 */
public class JDBCUtil {


    public static final String url = "jdbc:mysql://192.168.0.211/projectdb";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";

    public Connection conn = null;
    public PreparedStatement pst = null;






    public JDBCUtil(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static String sql = null;
    static JDBCUtil db1 = null;
    static ResultSet ret = null;


    public static void main(String[] args) {

        sql = "select * from api_v1_token where token='99e6b492c26f11e69df55254006ab3f9'";//SQL语句
        db1 = new JDBCUtil(sql);//创建DBHelper对象

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集





            while (ret.next()) {
                String uid = ret.getString(1);
                String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
