package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dto.TariffDto;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class TariffAdd implements Command {
    private String[] hasAccess = {StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(StringContainer.LANGUAGE);
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        String nameUkr = req.getParameter(StringContainer.NAME_UKR);
        String nameEn = req.getParameter(StringContainer.NAME_EN);
        Double price = Double.parseDouble(req.getParameter(StringContainer.PRICE));
        String service = req.getParameter(StringContainer.TARIFF_SERVICE);

        new TariffsServiceImpl().tariffCreate(nameUkr, nameEn, price, service);
        List<TariffDto> tariffs = new TariffsServiceImpl().getByService(service, language);
        req.setAttribute("tariffs", tariffs);
        return  "/WEB-INF/view/tariffs/" + role + "-tariff.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}