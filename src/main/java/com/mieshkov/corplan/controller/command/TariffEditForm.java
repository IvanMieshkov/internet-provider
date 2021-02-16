package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class TariffEditForm implements Command {
    private String[] hasAccess = {StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        Tariff tariff;
        int id;
        try {
            id = Integer.parseInt(req.getParameter(StringContainer.TARIFF_ID));
        } catch (NumberFormatException e) {
            return StringContainer.TARIFF_EDIT_ADD_PAGE;
        }
        tariff = new TariffsServiceImpl().getByTariffId(id);
        req.setAttribute("tariff", tariff);
        return StringContainer.TARIFF_EDIT_ADD_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
