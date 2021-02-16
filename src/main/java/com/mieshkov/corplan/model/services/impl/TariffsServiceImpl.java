package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.TariffDao;
import com.mieshkov.corplan.model.dto.TariffDto;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.services.TariffsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class TariffsServiceImpl implements TariffsService {
    private TariffDao tariffDao = DaoFactory.getInstance().createTariffDao();
    private List<TariffDto> tariffDtos = new ArrayList<>();

    @Override
    public List<TariffDto> getByUserId(Integer id, String language) {
        List<Tariff> tariffs = tariffDao.findByUserId(id);

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    @Override
    public List<TariffDto> getAllTariffs(String language) {
        List<Tariff> tariffs = tariffDao.findAll();

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    @Override
    public List<TariffDto> getByService(String tariffService, String language, String column, String order) {
        if(column == null) { column = "tariff_name_en"; }
        if(order == null) { order = "ASC"; }
        List<Tariff> tariffs = tariffDao.findByServiceSorted(tariffService, language, column, order);

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    @Override
    public List<TariffDto> getByService(String tariffService, String language) {
        String column = "tariff_name_en";
        String order = "ASC";
        List<Tariff> tariffs = tariffDao.findByServiceSorted(tariffService, language, column, order);

        finder(tariffs, language);

        tariffDao.close();
        return tariffDtos;
    }

    @Override
    public Tariff getByTariffId(Integer id) {
        Tariff tariff = tariffDao.findById(id);
        tariffDao.close();
        return tariff;
    }

    @Override
    public void tariffEdit(Integer id, String nameUkr, String nameEn, Double price, String service) {
        Tariff tariff = tariffDao.findById(id);
        tariff.setTariffNameEn(nameEn != null ? nameEn: tariff.getTariffNameEn());
        tariff.setTariffNameUkr(nameUkr != null ? nameUkr : tariff.getTariffNameUkr());
        tariff.setTariffPrice(price != 0 ? price : tariff.getTariffPrice());
        tariff.setTariffService(service != null ? service : tariff.getTariffService());

        tariffDao.update(tariff);
        tariffDao.close();
    }

    @Override
    public void tariffCreate(String nameUkr, String nameEn, Double price, String service) {
        Tariff tariff = new Tariff(nameUkr, nameEn, price, service);

        tariffDao.create(tariff);
        tariffDao.close();
    }

    private void finder(List<Tariff> tariffs, String language) {
        tariffs.forEach(tariff -> tariffDtos.add(new TariffDto(tariff)));
        tariffDtos.forEach(tariffDto -> tariffDto.setName(language.equals(StringContainer.LOCALE_UKR)
                                        ? tariffDto.getTariff().getTariffNameUkr()
                                        : tariffDto.getTariff().getTariffNameEn()));
    }
}
