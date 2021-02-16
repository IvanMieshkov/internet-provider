package com.mieshkov.corplan.model.dao.mapper;

import com.mieshkov.corplan.model.entities.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ivan Mieshkov
 */
public class TariffMapper implements ObjectMapper<Tariff> {
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("tariff_id");
        String tariffNameUkr = rs.getString("tariff_name_ukr");
        String tariffNameEn = rs.getString("tariff_name_en");
        Double tariffPrice = rs.getDouble("tariff_price");
        String tariffService = rs.getString("tariff_service");

        return new Tariff(id, tariffNameUkr, tariffNameEn, tariffPrice, tariffService);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param tariff} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of Appointment class instances
     * @param tariff POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param tariff}
     */
    @Override
    public Tariff makeUnique(Map<Long, Tariff> cache, Tariff tariff) {
        cache.putIfAbsent(tariff.getId(), tariff);

        return cache.get(tariff.getId());
    }
}
