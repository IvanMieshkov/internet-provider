package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class Menu implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
