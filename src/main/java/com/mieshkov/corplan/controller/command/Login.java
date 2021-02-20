package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.mieshkov.corplan.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class Login implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        if(role != null) {
            if(role.equals(StringContainer.ADMIN_ROLE)) {
                req.setAttribute(StringContainer.USERS_LIST, new UserServiceImpl().showAllUsers());
                return ADMIN_MENU;
            } else {
                return CLIENT_MENU;
            }
//            return  "/WEB-INF/view/menu/" + role + "-menu.jsp";
        }
        return StringContainer.LOGIN_PAGE;

    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}