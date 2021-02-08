package com.example.demo1.controller.command;

import com.example.demo1.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class StatusProcessing implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter(USER_ID));
        boolean active = !Boolean.parseBoolean(req.getParameter(ACTIVE));
        new UserServiceImpl().updateActive(id, active);

        req.setAttribute(USERS_LIST, new UserServiceImpl().showAllUsers());

        return USERS_LIST_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
