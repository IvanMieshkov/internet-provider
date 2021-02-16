package com.mieshkov.corplan.model.dto;

import com.mieshkov.corplan.model.entities.Service;

/**
 * @author Ivan Mieshkov
 */
public class ServiceDto {
    private Service service;
    private String name;

    public ServiceDto(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
