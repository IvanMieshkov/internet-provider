package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo1.containers.StringContainer.LOGIN_PAGE;
import static com.example.demo1.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * @author Ivan Mieshkov
 */
public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        if(role != null) {
            return  "/WEB-INF/view/menu/" + role + "-menu.jsp";
        }
        return LOGIN_PAGE;

    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}