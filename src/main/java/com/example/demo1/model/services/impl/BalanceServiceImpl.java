package com.example.demo1.model.services.impl;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.dao.UserTariffDao;
import com.example.demo1.model.services.BalanceService;

/**
 * @author Ivan Mieshkov
 */
public class BalanceServiceImpl implements BalanceService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    @Override
    public void changeBalance(Double amount) {

    }
}
