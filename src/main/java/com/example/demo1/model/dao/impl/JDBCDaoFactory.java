package com.example.demo1.model.dao.impl;

import com.example.demo1.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan Mieshkov
 */
public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

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
    public ServiceDao createServiceDao() {
        return new JDBCServiceDao(getConnection());
    }

    @Override
    public UserTariffDao createUserTariffDao() {
        return new JDBCUserTariffDao(getConnection());
    }

    //    @Override
//    public AppointmentDao createAppointmentDao() {
//        return new JDBCAppointmentDao(getConnection());
//    }
//
//    @Override
//    public ProcedureDao createProcedureDao() {
//        return new JDBCProcedureDao(getConnection());
//    }
//
//    @Override
//    public ReviewDao createReviewDao() {
//        return new JDBCReviewDao(getConnection());
//    }
//
//    @Override
//    public ClientAppointmentDao createClientAppointmentDao() {
//        return new JDBCClientAppointmentDao(getConnection());
//    }
//
//    @Override
//    public UserReviewDao createReviewDtoDao() {
//        return new JDBCUserReviewDao(getConnection());
//    }
//
//    @Override
//    public WorkingDayDao createWorkingDayDao() {
//        return new JDBCWorkingDayDao(getConnection());
//    }
}
