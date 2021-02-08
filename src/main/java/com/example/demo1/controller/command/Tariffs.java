package com.example.demo1.controller.command;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.services.impl.TariffsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static com.example.demo1.containers.StringContainer.*;

/**
 * Class for redirecting to the page with tariff catalogue
 * @author Ivan Mieshkov
 */
public class Tariffs implements Command {
    private String[] hasAccess = {CLIENT_ROLE, ADMIN_ROLE};
    /**
     * Method, that receives list of tariffs from tariffs service and redirects
     * to the tariff page, according to the role of logged user
     * @param req
     * @return page with the list of tariffs
     */
    @Override
    public String execute(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute(LANGUAGE);
        String role = (String) req.getSession().getAttribute(USER_LOGGED_ROLE);
        String service = req.getParameter(TARIFF_SERVICE);
        String sortBy = req.getParameter(SORT_BY);
        String order = req.getParameter(ORDER);

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
