package com.mieshkov.corplan.containers;

/**
 * @author Ivan Mieshkov
 */
public interface QueryContainer {

    String CREATE_USER = "INSERT INTO users(login_number, full_name_en, full_name_ukr, password," +
                            " email, address, phone_number, user_role)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login_number = ?";
    String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login_number = ? AND password = ?";
    String FIND_ALL_USERS = "SELECT * FROM users";
    String DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";
    String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?";
    String UPDATE_USER_BALANCE = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
    String UPDATE_USER_ACTIVE = "UPDATE users SET user_active = ? WHERE user_id = ?";
    String FIND_USER_BY_ROLE = "SELECT * FROM users WHERE user_role = ?";
    String FIND_USER_TARIFFS = "SELECT * FROM tariff " +
                                "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
                                "WHERE user_tariff.user_id = ?";

    String CREATE_TARIFF = "INSERT INTO tariff(tariff_name_ukr, tariff_name_en,tariff_price, tariff_service) " +
                            "VALUES (?, ?, ?, ?)";
    String FIND_TARIFF_BY_ID = "SELECT * FROM tariff WHERE tariff_id = ?";
    String FIND_ALL_TARIFFS = "SELECT * FROM tariff";
    String UPDATE_TARIFF = "UPDATE tariff SET tariff_name_en = ?, tariff_name_ukr = ?, " +
                            "tariff_price = ?, tariff_service = ? WHERE tariff_id = ?";
    String DELETE_TARIFF = "DELETE tariff WHERE tariff_id = ?";
    String CREATE_USER_TARIFF = "INSERT INTO user_tariff (user_id, tariff_id) " +
                                "SELECT ?, ? FROM DUAL WHERE NOT EXISTS " +
                                "(SELECT tariff.tariff_service, user_tariff.user_id, user_tariff.tariff_id FROM tariff "+
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
                                        "INNER JOIN user_tariff ON tariff.tariff_id = user_tariff.tariff_id " +
                                        "WHERE user_tariff.user_id=?;";
    String FIND_ALL_USER_TARIFF = "SELECT * FROM user_tariff";
    String UPDATE_USER_TARIFF = "UPDATE user_tariff INNER JOIN tariff ON user_tariff.tariff_id = tariff.tariff_id " +
                                "SET user_tariff.user_id = ?, user_tariff.tariff_id = ? " +
                                "WHERE tariff.tariff_service = ? AND user_tariff.user_id = ?";
    String FIND_ALL_SERVICES = "SELECT * FROM service";
}

