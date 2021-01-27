package com.example.demo1.model.services.impl;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.ServiceDao;
import com.example.demo1.model.dao.TariffDao;
import com.example.demo1.model.dto.ServiceDto;
import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.entities.Service;
import com.example.demo1.model.entities.Tariff;
import com.example.demo1.model.services.ServicesFinder;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.containers.StringContainer.LOCALE_UKR;

/**
 * @author Ivan Mieshkov
 */
public class ServicesService implements ServicesFinder {
    @Override
    public List<ServiceDto> getAllServices(String language) {

        ServiceDao serviceDao = DaoFactory.getInstance().createServiceDao();
        List<Service> services = serviceDao.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();

        services.forEach(service -> {
            serviceDtos.add(new ServiceDto(service));
        });

        serviceDtos.forEach(serviceDto -> serviceDto.setName(language.equals(LOCALE_UKR)
                                        ? serviceDto.getService().getServiceNameUkr()
                                        : serviceDto.getService().getServiceNameEn()));
        serviceDao.close();
        return serviceDtos;
    }
}
