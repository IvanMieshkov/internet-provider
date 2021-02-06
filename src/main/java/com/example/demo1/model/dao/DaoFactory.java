package com.example.demo1.model.dao;

import com.example.demo1.model.dao.impl.JDBCDaoFactory;
import com.example.demo1.model.entities.UserTariff;

/**
 * @author Ivan Mieshkov
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract TariffDao createTariffDao();
    public abstract ServiceDao createServiceDao();
    public abstract UserTariffDao createUserTariffDao();

//    public abstract AppointmentDao createAppointmentDao();
//    public abstract ReviewDao createReviewDao();
//    public abstract ClientAppointmentDao createClientAppointmentDao();
//    public abstract UserReviewDao createReviewDtoDao();
//    public abstract WorkingDayDao createWorkingDayDao();

    public static DaoFactory getInstance(){
        if (daoFactory == null) {
            synchronized (DaoFactory.class){
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }

        return daoFactory;
    }

}
