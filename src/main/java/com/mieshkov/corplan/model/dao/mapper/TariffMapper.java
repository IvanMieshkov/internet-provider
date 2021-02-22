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
        Long id = rs.getLong("id");
        String nameUkr = rs.getString("name_ukr");
        String nameEn = rs.getString("name_en");
        Double price = rs.getDouble("price");
        String service = rs.getString("service");

        return new Tariff(id, nameUkr, nameEn, price, service);
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
