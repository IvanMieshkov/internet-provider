package com.example.demo1.controller.command;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */

public class Payment implements Command {
    private String[] hasAccess = {CLIENT_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        User user = (User) req.getSession().getAttribute(USER_LOGGED);
        double amount = Double.parseDouble(req.getParameter(PAYMENT));
        double balance = user.getBalance() + amount;
        if(balance > 0) {
            user.setActive(true);
            new UserServiceImpl().updateActive(user.getId(), true);
        }

        new UserServiceImpl().changeBalance(user.getId(), amount);
        user.setBalance(balance);

        req.getSession().setAttribute(USER_LOGGED, user);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
