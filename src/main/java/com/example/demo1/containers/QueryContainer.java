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
    String FIND_USER_TARIFFS = "SELECT * FROM tariff " +
            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
            "WHERE user_tariff.user_id = ?";
//            "SELECT user_tariff.user_id, tariff.tariff_name_ukr, tariff.tariff_name_en FROM tariff " +
//            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
//            "WHERE user_tariff.user_id = ?";

    String CREATE_TARIFF = "INSERT INTO tariff(tariff_name, tariff_price) VALUES (?, ?)";
    String FIND_TARIFF_BY_ID = "SELECT * FROM tariff WHERE tariff_id = ?";
    String FIND_ALL_TARIFFS = "SELECT * FROM tariff";
    String FIND_TARIFF_BY_SERVICE = "SELECT * FROM tariff WHERE tariff_service = ? ORDER BY ? ?";
    String UPDATE_TARIFF = "UPDATE tariff SET tariff_price = ? WHERE tariff_id = ?";
    String DELETE_TARIFF = "DELETE tariff WHERE tariff_id = ?";

    String CREATE_USER_TARIFF = "INSERT INTO user_tariff (user_id, tariff_id) " +
            "SELECT ?, ? FROM DUAL WHERE NOT EXISTS " +
            "(SELECT tariff.tariff_service, user_tariff.user_id, user_tariff.tariff_id FROM tariff " +
            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
            "WHERE tariff_service = ? AND user_tariff.user_id = ? LIMIT 1)";

    String FIND_USER_TARIFF_BY_ID = "SELECT * FROM user_tariff WHERE user_tariff_id = ?";
    String FIND_TARIFFS_BY_USER_ID = "SELECT * FROM tariff " +
            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
            "WHERE user_tariff.user_id= ?";
    String FIND_TARIFFS_BY_USER_ID_AND_SERVICE = "SELECT * FROM tariff " +
            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
            "WHERE user_tariff.user_id= ? AND tariff.tariff_service = ?";
    String FIND_USERS_BY_TARIFF_ID = "SELECT * FROM user_tariff WHERE tariff_id = ?";
    String FIND_SERVICES_BY_USER_ID = "SELECT tariff.tariff_service, user_tariff.user_id FROM tariff " +
            "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id WHERE user_tariff.user_id=?;";
    String FIND_ALL_USER_TARIFF = "SELECT * FROM user_tariff";
    String UPDATE_USER_TARIFF = "UPDATE user_tariff INNER JOIN tariff ON user_tariff.tariff_id = tariff.tariff_id " +
            "SET user_tariff.user_id = ?, user_tariff.tariff_id = ? " +
            "WHERE tariff.tariff_service = ? AND user_tariff.user_id = ?";

    String FIND_ALL_SERVICES = "SELECT * FROM service";
    String SORT_BY_NAME_AND_PRICE_ASC = "SELECT * FROM tariff ORDER BY ? ?";
    String SORT_BY_NAME_AND_PRICE_DESC = "SELECT * FROM tariff ORDER BY ? DESC";
}

