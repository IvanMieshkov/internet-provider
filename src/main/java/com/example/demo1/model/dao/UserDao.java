package com.example.demo1.model.dao;

import com.example.demo1.model.entities.Tariff;
import com.example.demo1.model.entities.User;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    void updatePassword(Integer id, String newPassword);
    List<User> findByRole(String role);
}
