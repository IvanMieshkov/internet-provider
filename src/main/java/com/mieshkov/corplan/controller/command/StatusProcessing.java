package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class StatusProcessing implements Command {
    private String[] hasAccess = {StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter(StringContainer.USER_ID));
        boolean active = !Boolean.parseBoolean(req.getParameter(StringContainer.ACTIVE));
        new UserServiceImpl().updateActive(id, active);

        req.setAttribute(StringContainer.USERS_LIST, new UserServiceImpl().showAllUsers());

        return StringContainer.USERS_LIST_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
