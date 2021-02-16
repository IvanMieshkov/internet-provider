package com.mieshkov.corplan.model.dao;

import com.mieshkov.corplan.model.entities.UserTariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserTariffDao extends GenericDao<UserTariff> {
    List<UserTariff> findTariffsByUserIdAndService(Long id, String service);
    List<UserTariff> findTariffsByUserId(Long id);
    List<UserTariff> findUsersByTariffId(Long id);
    List<UserTariff> findServicesByUserId(Long id);



}
