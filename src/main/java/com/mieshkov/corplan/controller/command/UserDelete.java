package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class UserDelete implements Command {
    private final String[] hasAccess = {StringContainer.ADMIN_ROLE};
    private final UserServiceImpl userService;

    public UserDelete(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter(StringContainer.USER_ID));

        userService.delete(id);

        List<User> users = userService.showAllUsers();
        req.setAttribute(StringContainer.USERS_LIST, users);
        return StringContainer.ADMIN_MENU;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
