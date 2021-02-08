package com.example.demo1.controller.command;

import com.example.demo1.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;
import static com.example.demo1.containers.StringContainer.MAIN_PAGE;

/**
 * @author Ivan Mieshkov
 */
public class UsersList implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute(USERS_LIST, new UserServiceImpl().showAllUsers());
        return USERS_LIST_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
