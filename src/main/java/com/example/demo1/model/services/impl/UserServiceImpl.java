package com.example.demo1.model.services.impl;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.entities.User;
import com.example.demo1.model.exceptions.IncorrectDataInputException;
import com.example.demo1.model.exceptions.IncorrectPasswordException;
import com.example.demo1.model.exceptions.LoginAlreadyExistsException;
import com.example.demo1.model.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Objects;

import static com.example.demo1.containers.RegexContainer.PASSWORD_REGEX;
import static com.example.demo1.containers.StringContainer.INCORRECT_PASSWORD_WARNING;
import static com.example.demo1.containers.StringContainer.PASSWORD_INCORRECT;

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
    public void changeBalance(Integer id, Double amount) {
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
            throw new IncorrectPasswordException(INCORRECT_PASSWORD_WARNING);
        } else {

            checkByRegex(newPassword);
            Integer id = userDao.findByLoginAndPassword(user.getLogin(),
                    BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())).getId();

            DaoFactory.getInstance().createUserDao().updatePassword(id, newPassword);
            userDao.close();
        }
    }

    @Override
    public void updateActive(Integer id, Boolean active) {
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
            throw new IncorrectDataInputException(PASSWORD_INCORRECT);
        }
    }
}
