package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.DbProcessingException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class StatusProcessing implements Command {
    static final Logger LOG = LoggerFactory.getLogger(StatusProcessing.class);

    private final String[] hasAccess = {StringContainer.ADMIN_ROLE};
    private final UserServiceImpl userService;

    public StatusProcessing(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter(StringContainer.USER_ID));
        boolean active = !Boolean.parseBoolean(req.getParameter(StringContainer.ACTIVE));
        userService.updateActive(id, active);

        req.setAttribute(StringContainer.USERS_LIST, userService.showAllUsers());

        return StringContainer.ADMIN_MENU;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
