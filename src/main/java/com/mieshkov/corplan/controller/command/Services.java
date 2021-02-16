package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.services.impl.ServicesServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Mieshkov
 */
public class Services implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(StringContainer.LANGUAGE);
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);

        req.setAttribute("services", new ServicesServiceImpl().getAllServices(language));

        return "/WEB-INF/view/tariffs/" + role + "-services.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return false;
    }
}
