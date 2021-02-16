package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Mieshkov
 */
public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        if(role != null) {
            return  "/WEB-INF/view/menu/" + role + "-menu.jsp";
        }
        return StringContainer.LOGIN_PAGE;

    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}