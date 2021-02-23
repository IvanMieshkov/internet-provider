package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.TariffDao;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.services.TariffsService;
import com.mieshkov.corplan.utils.TariffPage;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class TariffsServiceImpl implements TariffsService {
    private TariffDao tariffDao = DaoFactory.getInstance().createTariffDao();

    @Override
    public TariffPage getByService(String service, String column,
                                     String order, int currentPage, int itemsPerPage) {
        return tariffDao.findByServiceSorted(service, column, order, currentPage, itemsPerPage);
    }

    @Override
    public List<Tariff> getAllByService(String service) {
        return tariffDao.findAllByService(service);
    }

    @Override
    public List<Tariff> findAll() {
        return tariffDao.findAll();
    }

    @Override
    public Tariff getByTariffId(Long id) {
        return tariffDao.findById(id);
    }

    @Override
    public void tariffEdit(Tariff tariff) {
        tariffDao.update(tariff);
    }

    @Override
    public void tariffCreate(Tariff tariff) {
        tariffDao.create(tariff);
    }

    @Override
    public void delete(Long id) {
        tariffDao.delete(id);
    }

}
