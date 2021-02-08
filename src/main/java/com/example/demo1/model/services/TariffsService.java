package com.example.demo1.model.services;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.entities.Tariff;

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
