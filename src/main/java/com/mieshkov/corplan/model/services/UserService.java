package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.exceptions.IncorrectPasswordException;
import com.mieshkov.corplan.model.exceptions.LoginAlreadyExistsException;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserService {
    void registerUser(User user) throws LoginAlreadyExistsException;
    void changeBalance(Long id, Double amount);
    void updatePassword(User user, String currentPassword, String newPassword)
            throws IncorrectPasswordException, IncorrectDataInputException;
    List<User> showAllUsers();
    void updateActive(Long id, Boolean active);
    User findByLogin(String login);
}
