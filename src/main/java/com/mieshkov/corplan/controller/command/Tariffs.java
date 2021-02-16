package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dto.TariffDto;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Class for redirecting to the page with tariff catalogue
 * @author Ivan Mieshkov
 */
public class Tariffs implements Command {
    private String[] hasAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};
    /**
     * Method, that receives list of tariffs from tariffs service and redirects
     * to the tariff page, according to the role of logged user
     * @param req
     * @return page with the list of tariffs
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(StringContainer.LANGUAGE);
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        String service = req.getParameter(StringContainer.TARIFF_SERVICE);
        String sortBy = req.getParameter(StringContainer.SORT_BY);
        String order = req.getParameter(StringContainer.ORDER);

        List<TariffDto> tariffs = new TariffsServiceImpl().getByService(service, language, sortBy, order);
        req.setAttribute("tariffs", tariffs);
        req.setAttribute("service", service);

        return "/WEB-INF/view/tariffs/" + role + "-tariff.jsp";
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
