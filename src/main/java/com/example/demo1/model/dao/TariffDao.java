package com.example.demo1.model.dao;

import com.example.demo1.model.entities.Tariff;

import javax.swing.text.Style;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffDao extends GenericDao<Tariff> {
    List<Tariff> findByServiceSorted(String tariffService, String language, String column, String order);

}
