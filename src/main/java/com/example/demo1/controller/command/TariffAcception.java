package com.example.demo1.controller.command;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.services.impl.UserTariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class TariffAcception implements Command {
    private String[] hasAccess = {CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        int tariff = Integer.parseInt(req.getParameter(SELECTED_TARIFF));
        String service = req.getParameter(TARIFF_SERVICE);

        User user = (User) req.getSession().getAttribute(USER_LOGGED);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        String language = (String) req.getSession().getAttribute(LANGUAGE);

        req.getSession().setAttribute(USER_TARIFFS, new UserTariffServiceImpl()
                .setUserTariffs(user.getId(), tariff, service));
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
