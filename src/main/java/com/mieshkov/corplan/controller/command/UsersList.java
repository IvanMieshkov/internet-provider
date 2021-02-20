package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.DbProcessingException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class UsersList implements Command {
    static final Logger LOGGER = Logger.getLogger(UsersList.class);
    private String[] hasAccess = {StringContainer.ADMIN_ROLE};
    private final UserServiceImpl userService;

    public UsersList(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<User> usersList = null;
        try {
            usersList = userService.showAllUsers();
        } catch (DbProcessingException e) {
            LOGGER.error("Error occurred while getting users list: {}", e);
            req.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/view/errors/error.jsp";
        }

        req.setAttribute(StringContainer.USERS_LIST, usersList);
        return StringContainer.USERS_LIST_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
