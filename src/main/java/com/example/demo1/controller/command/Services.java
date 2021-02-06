package com.example.demo1.controller.command;

import com.example.demo1.model.services.impl.ServicesServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo1.containers.StringContainer.*;
import static com.example.demo1.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * @author Ivan Mieshkov
 */
public class Services implements Command {
    private String[] hasAccess = {CLIENT_ROLE, ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);

        req.setAttribute("services", new ServicesServiceImpl().getAllServices(language));

        return "/WEB-INF/view/tariffs/" + role + "-services.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return false;
    }
}
