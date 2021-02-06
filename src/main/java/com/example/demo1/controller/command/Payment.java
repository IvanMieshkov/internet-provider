package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;
import static com.example.demo1.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * @author Ivan Mieshkov
 */

public class Payment implements Command {
    private String[] hasAccess = {CLIENT_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);

        Double amount = Double.parseDouble(req.getParameter(PAYMENT));


//        req.setAttribute("services", new ServicesService().getAllServices(language));

        return MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
