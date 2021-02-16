package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.entities.UserTariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserTariffsService {
    List<UserTariff> setUserTariffs(Long userId, Long tariffId, String service, double price);
    List<UserTariff> updateTariff(UserTariff userTariff);
    List<UserTariff> deleteTariff();
}
