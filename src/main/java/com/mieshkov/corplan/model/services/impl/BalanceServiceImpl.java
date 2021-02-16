package com.mieshkov.corplan.model.services.impl;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.services.BalanceService;

/**
 * @author Ivan Mieshkov
 */
public class BalanceServiceImpl implements BalanceService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public void changeBalance(Double amount) {

    }
}
