package com.example.demo1.controller.command;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class TariffAdd implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        String nameUkr = req.getParameter(NAME_UKR);
        String nameEn = req.getParameter(NAME_EN);
        Double price = Double.parseDouble(req.getParameter(PRICE));
        String service = req.getParameter(TARIFF_SERVICE);

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