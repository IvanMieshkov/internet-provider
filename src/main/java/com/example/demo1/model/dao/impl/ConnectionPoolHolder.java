package com.example.demo1.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    /**
     * Method, that creates DataSource object of database corplan_db
     * @return DataSource object
     */
    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(MYSQL_DRIVER);
                    ds.setUrl(DATABASE);
                    ds.setUsername(DATABASE_LOGIN);
                    ds.setPassword(DATABASE_PASSWORD);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
