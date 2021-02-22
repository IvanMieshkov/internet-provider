package com.mieshkov.corplan.model.dao;

import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.utils.TariffPage;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffDao extends GenericDao<Tariff> {
    TariffPage findByServiceSorted(String tariffService, String column,
                                   String order, int currentPage, int itemsPerPage);
    List<Tariff> findAllByService(String service);
    List<Tariff> findByUserId(Long id);
}
