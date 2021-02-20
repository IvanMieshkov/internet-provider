package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.mieshkov.corplan.containers.StringContainer.CLIENT_MENU;
import static com.mieshkov.corplan.containers.StringContainer.PAYMENT_INCORRECT;

/**
 * @author Ivan Mieshkov
 */

public class Payment implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        User user = (User) req.getSession().getAttribute(StringContainer.USER_LOGGED);
        double amount = Double.parseDouble(req.getParameter(StringContainer.PAYMENT));
        if(amount < 0) {
            req.setAttribute("warning", PAYMENT_INCORRECT);
            return CLIENT_MENU;
        }
        double balance = user.getBalance() + amount;
        if(balance > 0) {
            user.setActive(true);
            new UserServiceImpl().updateActive(user.getId(), true);
        }

        new UserServiceImpl().changeBalance(user.getId(), amount);
        user.setBalance(balance);

        req.getSession().setAttribute(StringContainer.USER_LOGGED, user);
        return CLIENT_MENU;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
