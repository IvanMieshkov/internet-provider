package com.mieshkov.corplan.model.dao.mapper;

import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.entities.UserTariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ivan Mieshkov
 */
public class UserTariffMapper implements ObjectMapper<UserTariff> {
    /**
     * Method for fetching data from result set
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public UserTariff extractFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Long userId = rs.getLong("user_id");
        Long tariffId = rs.getLong("tariff_id");
        String service = rs.getString("service");
        Tariff tariff = new TariffMapper().extractFromResultSet(rs);

        return new UserTariff(id, userId, tariffId, service, tariff);
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param userTariff} to {@param cache}
     * if there is no object with the same id
     * @param cache storage of UserTariff class instances
     * @param userTariff POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param userTariff}
     */
    @Override
    public UserTariff makeUnique(Map<Long, UserTariff> cache, UserTariff userTariff) {
        cache.putIfAbsent(userTariff.getId(), userTariff);

        return cache.get(userTariff.getId());
    }
}
