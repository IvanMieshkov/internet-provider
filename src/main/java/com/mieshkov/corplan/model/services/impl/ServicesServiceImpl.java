package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.ServiceDao;
import com.mieshkov.corplan.model.dto.ServiceDto;
import com.mieshkov.corplan.model.entities.Service;
import com.mieshkov.corplan.model.services.ServicesService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class ServicesServiceImpl implements ServicesService {
    @Override
    public List<ServiceDto> getAllServices(String language) {

        ServiceDao serviceDao = DaoFactory.getInstance().createServiceDao();
        List<Service> services = serviceDao.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();

        services.forEach(service -> {
            serviceDtos.add(new ServiceDto(service));
        });

        serviceDtos.forEach(serviceDto -> serviceDto.setName(language.equals(StringContainer.LOCALE_UKR)
                                        ? serviceDto.getService().getServiceNameUkr()
                                        : serviceDto.getService().getServiceNameEn()));
        serviceDao.close();
        return serviceDtos;
    }
}
