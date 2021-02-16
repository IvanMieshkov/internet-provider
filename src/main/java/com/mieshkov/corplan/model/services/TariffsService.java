package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.dto.TariffDto;
import com.mieshkov.corplan.model.entities.Tariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffsService {
    List<TariffDto> getAllTariffs(String language);
    List<TariffDto> getByService(String tariffService, String language, String column, String order);
    List<TariffDto> getByService(String tariffService, String language);
    List<TariffDto> getByUserId(Integer id, String language);
    Tariff getByTariffId(Integer id);
    void tariffEdit(Integer id, String nameUkr, String nameEn, Double price, String service);
    void tariffCreate(String nameUkr, String nameEn, Double price, String service);
}
