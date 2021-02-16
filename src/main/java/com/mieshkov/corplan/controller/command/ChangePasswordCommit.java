package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.exceptions.IncorrectPasswordException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.mieshkov.corplan.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class ChangePasswordCommit implements Command {
    private String[] hadAccess = {CLIENT_ROLE};

    /**
     * Method, that receives current password and new password and sends them
     * to the  user update service
     * @param req
     * @return main page
     */
    @Override
    public String execute(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute(USER_LOGGED);

        try {
            new UserServiceImpl().updatePassword(user,
                    req.getParameter("current-password"),
                    req.getParameter("new-password"));

        } catch (IncorrectPasswordException | IncorrectDataInputException e) {
            req.setAttribute("warning", e.getMessage());
            return CHANGE_PASSWORD_PAGE;
        }


        req.getSession().removeAttribute(USER_LOGGED);
        req.getSession().removeAttribute(USER_LOGGED_ROLE);
        return MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}

