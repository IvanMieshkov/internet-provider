package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import com.mieshkov.corplan.model.services.impl.UserTariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class TariffAccepting implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        String service = req.getParameter(StringContainer.TARIFF_SERVICE);
        User user = (User) req.getSession().getAttribute(StringContainer.USER_LOGGED);
        Long tariff = Long.parseLong(req.getParameter(StringContainer.SELECTED_TARIFF));
        double price = Double.parseDouble(req.getParameter("price"));

        double balance = user.getBalance() - price;
        if(balance < 0) {
            user.setActive(false);
            new UserServiceImpl().updateActive(user.getId(), false);
        }
        user.setBalance(balance);

        req.getSession().setAttribute(StringContainer.USER_TARIFFS, new UserTariffServiceImpl()
                .setUserTariffs(user.getId(), tariff, service, price));
        req.getSession().setAttribute(StringContainer.USER_LOGGED, user);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
