package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class Logout implements Command {
    private String[] hadAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute(StringContainer.USER_LOGGED);
        session.removeAttribute(StringContainer.USER_LOGGED_ROLE);

        return StringContainer.MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
