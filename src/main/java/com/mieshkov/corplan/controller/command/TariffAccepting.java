package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import com.mieshkov.corplan.model.services.impl.UserTariffServiceImpl;
import com.mieshkov.corplan.utils.TariffPage;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static com.mieshkov.corplan.containers.StringContainer.CLIENT_MENU;
import static com.mieshkov.corplan.containers.StringContainer.CLIENT_TARIFF;

/**
 * @author Ivan Mieshkov
 */
public class TariffAccepting implements Command {
    private final String[] hasAccess = {StringContainer.CLIENT_ROLE};
    private final UserServiceImpl userService;
    private final UserTariffServiceImpl userTariffService;

    public TariffAccepting(UserServiceImpl userService, UserTariffServiceImpl userTariffService) {
        this.userService = userService;
        this.userTariffService = userTariffService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String service = req.getParameter(StringContainer.TARIFF_SERVICE);
        User user = (User) req.getSession().getAttribute(StringContainer.USER_LOGGED);
        Long tariffId = Long.parseLong(req.getParameter(StringContainer.TARIFF_ID));
        Double price = Double.parseDouble(req.getParameter("price"));
        if(user.getBalance() < 0) {

            req.setAttribute("warning", StringContainer.LOW_BALANCE);
            req.getSession().setAttribute(StringContainer.USER_LOGGED, user);
            req.getSession().setAttribute(StringContainer.USER_TARIFFS,
                    userTariffService.setUserTariffs(user.getId(), tariffId, service, price));
            return CLIENT_MENU;
        }

        double balance = user.getBalance() - price;
        if(balance < 0) {
            user.setActive(false);
            userService.updateActive(user.getId(), false);
        }
        user.setBalance(balance);

        req.getSession().setAttribute(StringContainer.USER_TARIFFS,
                                        userTariffService.setUserTariffs(user.getId(), tariffId, service, price));
        req.getSession().setAttribute(StringContainer.USER_LOGGED, user);
        return CLIENT_MENU;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
