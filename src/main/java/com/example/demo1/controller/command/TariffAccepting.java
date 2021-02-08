package com.example.demo1.controller.command;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.services.impl.UserServiceImpl;
import com.example.demo1.model.services.impl.UserTariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class TariffAccepting implements Command {
    private String[] hasAccess = {CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        String service = req.getParameter(TARIFF_SERVICE);
        User user = (User) req.getSession().getAttribute(USER_LOGGED);
        int tariff = Integer.parseInt(req.getParameter(SELECTED_TARIFF));
        double price = Double.parseDouble(req.getParameter("price"));

        double balance = user.getBalance() - price;
        if(balance < 0) {
            user.setActive(false);
            new UserServiceImpl().updateActive(user.getId(), false);
        }
        user.setBalance(balance);

        req.getSession().setAttribute(USER_TARIFFS, new UserTariffServiceImpl()
                .setUserTariffs(user.getId(), tariff, service, price));
        req.getSession().setAttribute(USER_LOGGED, user);
        return "/WEB-INF/view/menu/" + role + "-menu.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
