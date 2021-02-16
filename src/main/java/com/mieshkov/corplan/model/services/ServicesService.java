package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.dto.ServiceDto;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface ServicesService {
    List<ServiceDto> getAllServices(String language);
}
