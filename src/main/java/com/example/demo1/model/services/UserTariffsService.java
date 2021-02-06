package com.example.demo1.model.services;

import com.example.demo1.model.dao.UserTariffDao;
import com.example.demo1.model.entities.UserTariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserTariffsService {
    List<UserTariff> setUserTariffs(int userId, int tariffId, String service);
    List<UserTariff> updateTariff(UserTariff userTariff);
    List<UserTariff> deleteTariff();
}
