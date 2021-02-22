package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.dao.UserTariffDao;
import com.mieshkov.corplan.model.entities.UserTariff;
import com.mieshkov.corplan.model.services.UserTariffsService;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class UserTariffServiceImpl implements UserTariffsService {
    private final UserTariffDao userTariffDao = DaoFactory.getInstance().createUserTariffDao();
    private final UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public List<UserTariff> setUserTariffs(Long userId, Long tariffId, String service, double price) {
        UserTariff userTariff = new UserTariff(userId,tariffId, service);
        List<UserTariff> tariffs = userTariffDao.findTariffsByUserIdAndService(userId, service);
        if(tariffs.size() == 0) {
            userTariffDao.create(userTariff);
        } else {
            userTariffDao.update(userTariff);
            tariffs.clear();
        }
        userDao.updateUserBalance(userId, -price);
        return userTariffDao.findTariffsByUserId(userId);
    }
}
