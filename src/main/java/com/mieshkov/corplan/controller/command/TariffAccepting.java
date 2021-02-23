package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import com.mieshkov.corplan.model.services.impl.UserTariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.mieshkov.corplan.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class TariffAccepting implements Command {
    private final String[] hasAccess = {CLIENT_ROLE};
    private final UserServiceImpl userService;
    private final UserTariffServiceImpl userTariffService;

    public TariffAccepting(UserServiceImpl userService, UserTariffServiceImpl userTariffService) {
        this.userService = userService;
        this.userTariffService = userTariffService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String service = req.getParameter(TARIFF_SERVICE);
        User user = (User) req.getSession().getAttribute(USER_LOGGED);
        boolean active = userService.findById(user.getId()).getActive();
        Long tariffId = Long.parseLong(req.getParameter(TARIFF_ID));
        Double price = Double.parseDouble(req.getParameter("price"));
        if(user.getBalance() < 0) {
            req.setAttribute("warning", LOW_BALANCE);
            req.getSession().setAttribute(USER_LOGGED, user);
            req.getSession().setAttribute(USER_TARIFFS,
                    userTariffService.setUserTariffs(user.getId(), tariffId, service, price));
            return CLIENT_MENU;
        }
        if(!active) {
            req.setAttribute("warning", BLOCKED);
            req.getSession().setAttribute(USER_LOGGED, user);
            req.getSession().setAttribute(USER_TARIFFS,
                    userTariffService.setUserTariffs(user.getId(), tariffId, service, price));
            return CLIENT_MENU;
        }


        double balance = user.getBalance() - price;
        if(balance < 0) {
            user.setActive(false);
            userService.updateActive(user.getId(), false);
        }
        user.setBalance(balance);

        req.getSession().setAttribute(USER_TARIFFS,
                                        userTariffService.setUserTariffs(user.getId(), tariffId, service, price));
        req.getSession().setAttribute(USER_LOGGED, user);
        return CLIENT_MENU;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
