package com.mieshkov.corplan.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Ivan Mieshkov
 */
public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    /**
     * Method, that creates DataSource object of database corplan_db
     * @return DataSource object
     */
    public static DataSource getDataSource() {
        Properties properties = new Properties();
        try (InputStream in = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(properties.getProperty("connection.driver"));
                    ds.setUrl(properties.getProperty("connection.url"));
                    ds.setUsername(properties.getProperty("connection.username"));
                    ds.setPassword(properties.getProperty("connection.password"));
                    ds.setMaxOpenPreparedStatements(100);
                    ds.setMaxActive(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }
}
