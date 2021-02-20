package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.exceptions.IncorrectPasswordException;
import com.mieshkov.corplan.model.exceptions.LoginAlreadyExistsException;
import com.mieshkov.corplan.model.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

import static com.mieshkov.corplan.containers.RegexContainer.PASSWORD_REGEX;

/**
 * @author Ivan Mieshkov
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public void registerUser(User user) throws LoginAlreadyExistsException{
        if (Objects.nonNull(userDao.findByLogin(user.getLogin()))) {
            throw new LoginAlreadyExistsException();
        }
        userDao.create(user);
        userDao.close();
    }

    @Override
    public void changeBalance(Long id, Double amount) {
        userDao.updateUserBalance(id, amount);
        userDao.close();
    }

    @Override
    public List<User> showAllUsers() {
        List<User> userList = userDao.findAll();
        userDao.close();
        return userList;
    }

    @Override
    public void updatePassword(User user, String currentPassword, String newPassword)
                                            throws IncorrectPasswordException, IncorrectDataInputException {
        if (!BCrypt.checkpw(currentPassword, user.getPassword())) {
            throw new IncorrectPasswordException(StringContainer.INCORRECT_PASSWORD_WARNING);
        } else {
            checkByRegex(newPassword);
            Long id = userDao.findByLoginAndPassword(user.getLogin(), user.getPassword()).getId();

            final String newHashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            DaoFactory.getInstance().createUserDao().updatePassword(id, newHashPassword);
            userDao.close();
        }
    }

    @Override
    public void updateActive(Long id, Boolean active) {
        userDao.updateUserActive(id, active);
        userDao.close();
    }

    @Override
    public User findByLogin(String login) {
        User user = userDao.findByLogin(login);
        userDao.close();
        return user;
    }

    private void checkByRegex(String password) throws IncorrectDataInputException {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(StringContainer.PASSWORD_INCORRECT);
        }
    }
}
