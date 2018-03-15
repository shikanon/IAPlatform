package com.ue.dao;

public interface TDataSetDao {

    public TDataSetDao upload(TDataSetDao dataset, String uuid);

    public void delete(String uuid);

}
