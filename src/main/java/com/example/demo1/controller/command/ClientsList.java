package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;
import static com.example.demo1.containers.StringContainer.MAIN_PAGE;

/**
 * @author Ivan Mieshkov
 */
public class ClientsList implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        return CLIENTS_LIST_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
