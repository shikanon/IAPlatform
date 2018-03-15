package com.ue.dao.daoImpl;

import com.ue.dao.BaseSqlDao;
import com.ue.entity.Program;
import com.ue.model.TProgram;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明
 * @Date: created in 16:33 2017/11/13
 */
@Repository("baseSqlDao")
public class BaseSqlDaoImpl implements BaseSqlDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 查询操作
      *@param: 参数说明 sql语句
      *@Date: created in 16:34 2017/11/13
      */
    @Override
    public List queryBySql(String sql) {
        List<Object[]> list = this.getCurrentSession().createSQLQuery(sql).list();
        return list;
    }

    /**
      *@auther: 作者 dzc
      *@description: 类说明 执行操作（增删改）
      *@param: 参数说明 sql语句
      *@Date: created in 16:35 2017/11/13
      */
    @Override
    public int excuteBySql(String sql) {
        int result ;
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        result = query.executeUpdate();
        return result;
    }
}
