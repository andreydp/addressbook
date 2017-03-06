package com.levelup.dao;

/**
 * Created by java on 24.02.2017.
 */
public interface DataProvider {

    enum ConnectionType {
        MYSQL, H2, MONGODB, CSV, JSON, XML
    }

    void openConnection();

    void closeConnection();
}
