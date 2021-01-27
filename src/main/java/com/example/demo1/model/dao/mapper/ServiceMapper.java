package com.example.demo1.model.dao.mapper;

import com.example.demo1.model.entities.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ivan Mieshkov
 */
public class ServiceMapper implements ObjectMapper<Service> {
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("service_id");
        String serviceNameUkr = rs.getString("service_name_ukr");
        String serviceNameEn = rs.getString("service_name_en");

        return new Service(id, serviceNameUkr, serviceNameEn);
    }

    @Override
    public Service makeUnique(Map<Integer, Service> cache, Service service) {
        cache.putIfAbsent(service.getId(), service);
        return cache.get(service.getId());
    }
}
