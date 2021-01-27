package com.example.demo1.model.services;

import com.example.demo1.model.dto.ServiceDto;
import com.example.demo1.model.dto.TariffDto;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface ServicesFinder {
    List<ServiceDto> getAllServices(String language);
}
