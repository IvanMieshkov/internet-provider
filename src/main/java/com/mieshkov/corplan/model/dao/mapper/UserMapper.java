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

        Long id = rs.getLong("user_id");
        String login = rs.getString("login_number");
        String fullNameEn = rs.getString("full_name_en");
        String fullNameUkr = rs.getString("full_name_ukr");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String address = rs.getString("address");
        String phoneNumber = rs.getString("phone_number");
        Double balance = rs.getDouble("balance");
        String role = rs.getString("user_role");
        Boolean active = rs.getBoolean("user_active");

        return new User(id, login, fullNameEn, fullNameUkr, password,
                email, address, phoneNumber, balance, role, active);
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