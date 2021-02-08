package com.example.demo1.model.services.impl;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.dao.UserTariffDao;
import com.example.demo1.model.entities.UserTariff;
import com.example.demo1.model.services.UserTariffsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class UserTariffServiceImpl implements UserTariffsService {
    private UserTariffDao userTariffDao = DaoFactory.getInstance().createUserTariffDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private List<UserTariff> tariffs = new ArrayList<>();
    private List<UserTariff> updatedTariffs = new ArrayList<>();



    @Override
    public List<UserTariff> setUserTariffs(int userId, int tariffId, String service, double price) {
        UserTariff userTariff = new UserTariff(userId,tariffId, service);
        tariffs = userTariffDao.findTariffsByUserIdAndService(userId, service);
        if(tariffs.size() == 0) {
            userTariffDao.create(userTariff);
        } else {
            userTariffDao.update(userTariff);
            tariffs.clear();
        }
        userDao.updateUserBalance(userId, -price);
        userDao.close();
        tariffs = userTariffDao.findTariffsByUserId(userId);

        userTariffDao.close();
        return tariffs;
    }

    @Override
    public List<UserTariff> updateTariff(UserTariff userTariff) {
        userTariffDao.update(userTariff);
        userTariffDao.close();
        return null;
    }

    @Override
    public List<UserTariff> deleteTariff() {
        userTariffDao.close();
        return null;
    }
}
