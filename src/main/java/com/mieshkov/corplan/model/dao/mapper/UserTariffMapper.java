package com.mieshkov.corplan.model.dao.mapper;

import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.entities.UserTariff;

import java.nio.file.attribute.UserDefinedFileAttributeView;
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

        UserTariff userTariff = new UserTariff();
        userTariff.setId(rs.getLong("id"));
        userTariff.setUserId(rs.getLong("user_id"));
        userTariff.setTariffId(rs.getLong("tariff_id"));
        userTariff.setService(rs.getString("service"));
        userTariff.setTariff(new TariffMapper().extractFromResultSet(rs));

        return userTariff;
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
