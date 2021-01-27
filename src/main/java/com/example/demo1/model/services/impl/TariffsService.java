package com.example.demo1.model.services.impl;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.TariffDao;
import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.entities.Tariff;
import com.example.demo1.model.services.TariffsFinder;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.containers.StringContainer.LOCALE_UKR;

/**
 * @author Ivan Mieshkov
 */
public class TariffsService implements TariffsFinder {
    private TariffDao tariffDao = DaoFactory.getInstance().createTariffDao();
    private List<TariffDto> tariffDtos = new ArrayList<>();

    @Override
    public List<TariffDto> getAllTariffs(String language) {
        List<Tariff> tariffs = tariffDao.findAll();

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    @Override
    public List<TariffDto> getByService(String tariffService, String language, String column, String order) {
        List<Tariff> tariffs = tariffDao.findByServiceSorted(tariffService, language, column, order);

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    private void finder(List<Tariff> tariffs, String language) {
        tariffs.forEach(tariff -> {
            tariffDtos.add(new TariffDto(tariff));
                });
        tariffDtos.forEach(tariffDto -> tariffDto.setName(language.equals(LOCALE_UKR)
                                        ? tariffDto.getTariff().getTariffNameUkr()
                                        : tariffDto.getTariff().getTariffNameEn()));
    }
}
