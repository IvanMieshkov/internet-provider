package com.mieshkov.corplan.model.dao;

import com.mieshkov.corplan.model.entities.User;

/**
 * @author Ivan Mieshkov
 */
public interface UserDao extends GenericDao<User> {
    User findByLoginAndPassword(Long id, String password);
    void updatePassword(Long id, String newPassword);
    void updateUserBalance(Long id, Double payment);
    void updateUserActive(Long id, Boolean active);
}
