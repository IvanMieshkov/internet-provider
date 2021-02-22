package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.EmailAlreadyExistsException;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.exceptions.IncorrectPasswordException;
import com.mieshkov.corplan.model.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

import static com.mieshkov.corplan.containers.RegexContainer.PASSWORD_REGEX;

/**
 * @author Ivan Mieshkov
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public void registerUser(User user) throws EmailAlreadyExistsException {
        userDao.create(user);
    }

    @Override
    public void changeBalance(Long id, Double amount) {
        userDao.updateUserBalance(id, amount);
    }

    @Override
    public List<User> showAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void updatePassword(User user, String currentPassword, String newPassword)
                                            throws IncorrectPasswordException, IncorrectDataInputException {
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            throw new IncorrectPasswordException(StringContainer.INCORRECT_PASSWORD_WARNING);
        } else {
            checkByRegex(newPassword);
            Long id = userDao.findByLoginAndPassword(user.getId(), user.getPassword()).getId();

            final String newHashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            DaoFactory.getInstance().createUserDao().updatePassword(id, newHashPassword);
        }
    }

    @Override
    public void updateActive(Long id, Boolean active) {
        userDao.updateUserActive(id, active);
    }


    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }


    private void checkByRegex(String password) throws IncorrectDataInputException {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(StringContainer.PASSWORD_INCORRECT);
        }
    }


}
