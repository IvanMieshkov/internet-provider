package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class Logout implements Command {
    private String[] hadAccess = {CLIENT_ROLE, ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.removeAttribute(USER_LOGGED);
        session.removeAttribute(USER_LOGGED_ROLE);

        return MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
