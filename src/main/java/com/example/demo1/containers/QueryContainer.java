package com.example.demo1.containers;

/**
 * @author Ivan Mieshkov
 */
public interface QueryContainer {

    String CREATE_USER = "INSERT INTO users(login_number, full_name, password," +
            " email, address, phone_number, balance, user_role)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login_number = ?";
    String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login_number = ? AND password = ?";
    String FIND_ALL_USERS = "SELECT * FROM users";
    String DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";
    String UPDATE_USER_PASSWORD = "UPDATE users SET user_password = ? WHERE user_id = ?";
    String FIND_USER_BY_ROLE = "SELECT * FROM users WHERE user_role = ?";

    String CREATE_TARIFF = "INSERT INTO tariff(tariff_name, tariff_price) VALUES (?, ?)";
    String FIND_TARIFF_BY_ID = "SELECT * FROM tariff WHERE tariff_id = ?";
    String FIND_ALL_TARIFFS = "SELECT * FROM tariff";
    String FIND_TARIFF_BY_SERVICE = "SELECT * FROM tariff WHERE tariff_service = ? ORDER BY ? ?";
    String UPDATE_TARIFF = "UPDATE tariff SET tariff_price = ? WHERE tariff_id = ?";
    String DELETE_TARIFF = "DELETE tariff WHERE tariff_id = ?";

    String FIND_ALL_SERVICES = "SELECT * FROM service";
    String SORT_BY_NAME_AND_PRICE_ASC = "SELECT * FROM tariff ORDER BY ? ?";
    String SORT_BY_NAME_AND_PRICE_DESC = "SELECT * FROM tariff ORDER BY ? DESC";
}

