package com.example.demo1.model.dao.mapper;

import com.example.demo1.model.entities.Tariff;
import com.example.demo1.model.entities.User;
import com.example.demo1.model.entities.UserTariff;

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
        Integer id = rs.getInt("user_tariff_id");
        Integer userId = rs.getInt("user_id");
        Integer tariffId = rs.getInt("tariff_id");
        String service = rs.getString("tariff_service");
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
    public UserTariff makeUnique(Map<Integer, UserTariff> cache, UserTariff userTariff) {
        cache.putIfAbsent(userTariff.getId(), userTariff);

        return cache.get(userTariff.getId());
    }
}
