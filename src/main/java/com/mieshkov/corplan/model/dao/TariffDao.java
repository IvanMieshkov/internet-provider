package com.mieshkov.corplan.model.dao;

import com.mieshkov.corplan.model.entities.Tariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findByServiceSorted(String tariffService, String language, String column, String order);
    List<Tariff> findByUserId(Integer id);
}
