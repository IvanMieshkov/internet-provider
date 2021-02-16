package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.dao.UserTariffDao;
import com.mieshkov.corplan.model.entities.UserTariff;
import com.mieshkov.corplan.model.services.UserTariffsService;

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
    public List<UserTariff> setUserTariffs(Long userId, Long tariffId, String service, double price) {
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
