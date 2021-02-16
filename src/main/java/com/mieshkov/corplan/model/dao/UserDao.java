package com.mieshkov.corplan.model.dao;

import com.mieshkov.corplan.model.entities.User;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    void updatePassword(Long id, String newPassword);
    List<User> findByRole(String role);
    void updateUserBalance(Long id, Double payment);
    void updateUserActive(Long id, Boolean active);
}
