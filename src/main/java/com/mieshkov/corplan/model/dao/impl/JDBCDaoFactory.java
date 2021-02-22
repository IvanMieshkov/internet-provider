package com.mieshkov.corplan.model.dao.impl;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.TariffDao;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.dao.UserTariffDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Mieshkov
 */
public class JDBCDaoFactory extends DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection(){
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public TariffDao createTariffDao() {
        return new JDBCTariffDao(getConnection());
    }

    @Override
    public UserTariffDao createUserTariffDao() {
        return new JDBCUserTariffDao(getConnection());
    }

}
