package com.example.demo1.controller.command;

import com.example.demo1.model.entities.Tariff;
import com.example.demo1.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class TariffEditForm implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        Tariff tariff;
        int id;
        try {
            id = Integer.parseInt(req.getParameter(TARIFF_ID));
        } catch (NumberFormatException e) {
            return TARIFF_EDIT_ADD_PAGE;
        }
        tariff = new TariffsServiceImpl().getByTariffId(id);
        req.setAttribute("tariff", tariff);
        return TARIFF_EDIT_ADD_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}
