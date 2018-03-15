package com.ue.dao.daoImpl;

import com.ue.dao.TDataSetDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TDataSetImpl implements TDataSetDao {

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


    public TDataSetDao upload(TDataSetDao dataset, String uuid){
        return dataset;
    }
    public void delete(String uuid){

    }

}
