package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.mieshkov.corplan.containers.StringContainer.ADMIN_MENU;
import static com.mieshkov.corplan.containers.StringContainer.CLIENT_MENU;

/**
 * @author Ivan Mieshkov
 */
public class Login implements Command {
    private final UserServiceImpl userService;

    public Login(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        if(role != null) {
            if(role.equals(StringContainer.ADMIN_ROLE)) {
                req.setAttribute(StringContainer.USERS_LIST, userService.showAllUsers());
                return ADMIN_MENU;
            } else {
                return CLIENT_MENU;
            }
        }
        return StringContainer.LOGIN_PAGE;

    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}