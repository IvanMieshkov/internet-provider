package com.example.demo1.model.dao;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.entities.UserTariff;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserTariffDao extends GenericDao<UserTariff> {
    List<UserTariff> findTariffsByUserIdAndService(Integer id, String service);
    List<UserTariff> findTariffsByUserId(Integer id);
    List<UserTariff> findUsersByTariffId(Integer id);
    List<UserTariff> findServicesByUserId(Integer id);



}
