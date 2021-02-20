package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.mieshkov.corplan.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class Menu implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);

        if(role.equals(StringContainer.ADMIN_ROLE)) {
            req.setAttribute(StringContainer.USERS_LIST, new UserServiceImpl().showAllUsers());
            return ADMIN_MENU;
        } else {
            return CLIENT_MENU;
        }
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
