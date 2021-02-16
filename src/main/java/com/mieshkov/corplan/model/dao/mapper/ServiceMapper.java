package com.mieshkov.corplan.model.dao.mapper;

import com.mieshkov.corplan.model.entities.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ivan Mieshkov
 */
public class ServiceMapper implements ObjectMapper<Service> {
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("service_id");
        String serviceNameUkr = rs.getString("service_name_ukr");
        String serviceNameEn = rs.getString("service_name_en");

        return new Service(id, serviceNameUkr, serviceNameEn);
    }

    @Override
    public Service makeUnique(Map<Long, Service> cache, Service service) {
        cache.putIfAbsent(service.getId(), service);
        return cache.get(service.getId());
    }
}
