package com.mieshkov.corplan.containers;

/**
 * @author Ivan Mieshkov
 */
public interface QueryContainer {

    String CREATE_USER = "INSERT INTO user(name_en, name_ukr, password," +
                            " email, address, phone_number, role)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE id = ? AND password = ?";
    String FIND_ALL_USERS = "SELECT * FROM user";
    String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    String UPDATE_USER_PASSWORD = "UPDATE user SET password = ? WHERE id = ?";
    String UPDATE_USER_BALANCE = "UPDATE user SET balance = balance + ? WHERE id = ?";
    String UPDATE_USER_ACTIVE = "UPDATE user SET active = ? WHERE id = ?";
    String FIND_USER_TARIFFS = "SELECT * FROM tariff " +
                                "INNER JOIN user_has_tariff ON tariff.id = user_has_tariff.tariff_id " +
                                "WHERE user_has_tariff.user_id = ?";
    String FIND_TARIFFS_BY_SERVICE = "SELECT * FROM tariff WHERE service = ?";

    String CREATE_TARIFF = "INSERT INTO tariff(name_ukr, name_en, price, service) " +
                            "VALUES (?, ?, ?, ?)";
    String FIND_TARIFF_BY_ID = "SELECT * FROM tariff WHERE id = ?";
    String FIND_ALL_TARIFFS = "SELECT * FROM tariff";
    String UPDATE_TARIFF = "UPDATE tariff SET name_en = ?, name_ukr = ?, " +
                            "price = ?, service = ? WHERE id = ?";
    String DELETE_TARIFF = "DELETE FROM tariff WHERE id = ?";
    String CREATE_USER_TARIFF = "INSERT INTO user_has_tariff (user_id, tariff_id) " +
                                "SELECT ?, ? FROM DUAL WHERE NOT EXISTS " +
                                "(SELECT tariff.service, user_has_tariff.user_id, user_has_tariff.tariff_id FROM tariff "+
                                "INNER JOIN user_has_tariff ON tariff.id = user_has_tariff.tariff_id " +
                                "WHERE service = ? AND user_has_tariff.user_id = ? LIMIT 1)";

    String FIND_USER_TARIFF_BY_ID = "SELECT * FROM user_has_tariff WHERE id = ?";
    String FIND_TARIFFS_BY_USER_ID = "SELECT * FROM tariff " +
                                        "INNER JOIN user_has_tariff ON tariff.id = user_has_tariff.tariff_id " +
                                        "WHERE user_has_tariff.user_id= ?";
    String FIND_TARIFFS_BY_USER_ID_AND_SERVICE = "SELECT * FROM tariff " +
                                                "INNER JOIN user_has_tariff ON tariff.id = user_has_tariff.tariff_id " +
                                                "WHERE user_has_tariff.user_id= ? AND tariff.service = ?";
    String FIND_ALL_USER_TARIFF = "SELECT * FROM user_has_tariff";
    String UPDATE_USER_TARIFF = "UPDATE user_has_tariff INNER JOIN tariff ON user_has_tariff.tariff_id = tariff.id " +
                                "SET user_has_tariff.user_id = ?, user_has_tariff.tariff_id = ? " +
                                "WHERE tariff.service = ? AND user_has_tariff.user_id = ?";
}
