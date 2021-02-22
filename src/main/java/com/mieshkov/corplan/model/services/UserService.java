package com.mieshkov.corplan.model.services;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.EmailAlreadyExistsException;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.exceptions.IncorrectPasswordException;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public interface UserService {
    void registerUser(User user) throws EmailAlreadyExistsException;
    void changeBalance(Long id, Double amount);
    void updatePassword(User user, String currentPassword, String newPassword)
                                    throws IncorrectPasswordException, IncorrectDataInputException;
    List<User> showAllUsers();
    void updateActive(Long id, Boolean active);
    void delete(Long id);
    User findById(Long id);
}
