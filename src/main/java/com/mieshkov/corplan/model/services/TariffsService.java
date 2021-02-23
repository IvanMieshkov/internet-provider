package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.utils.TariffPage;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffsService {
    TariffPage getByService(String tariffService, String column, String order, int currentPage, int itemsPerPage) throws SQLException;
    List<Tariff> getAllByService(String service);
    List<Tariff> findAll();
    Tariff getByTariffId(Long id);
    void tariffEdit(Tariff tariff);
    void tariffCreate(Tariff tariff);
    void delete(Long id);
}
