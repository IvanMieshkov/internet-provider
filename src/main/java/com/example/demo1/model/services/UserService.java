package com.example.demo1.model.services;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.exceptions.IncorrectDataInputException;
import com.example.demo1.model.exceptions.IncorrectPasswordException;
import com.example.demo1.model.exceptions.LoginAlreadyExistsException;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserService {
    void registerUser(User user) throws LoginAlreadyExistsException;
    void changeBalance(Integer id, Double amount);
    void updatePassword(User user, String currentPassword, String newPassword)
            throws IncorrectPasswordException, IncorrectDataInputException;
    List<User> showAllUsers();
    void updateActive(Integer id, Boolean active);
    User findByLogin(String login);
}
