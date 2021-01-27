package com.example.demo1.model.services;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.entities.Tariff;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface TariffsFinder {
    List<TariffDto> getAllTariffs(String language);
    List<TariffDto> getByService(String tariffService, String language, String column, String order);
}
