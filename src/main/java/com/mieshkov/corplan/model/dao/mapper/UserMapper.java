package com.mieshkov.corplan.model.dao.mapper;

import com.mieshkov.corplan.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Ivan Mieshkov
 */
public class UserMapper implements ObjectMapper<User> {
    /**
     * Method for fetching data from result set
     *
     * @param rs, that contains data to fetch
     * @return POJO object with fetched data
     * @throws SQLException
     */
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setNameEn(rs.getString("name_en"));
        user.setNameUkr(rs.getString("name_ukr"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setBalance(rs.getDouble("balance"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));

        return user;
    }

    /**
     * Method, that allows not to store repeating POJO objects. Method put {@param user} to {@param cache}
     * if there is no object with the same id
     *
     * @param cache storage of Appointment class instances
     * @param user  POJO object, that should be added to cache
     * @return value from {@param cache} with the key id from {@param user}
     */
    @Override
    public User makeUnique(Map<Long, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);

        return cache.get(user.getId());
    }
}
