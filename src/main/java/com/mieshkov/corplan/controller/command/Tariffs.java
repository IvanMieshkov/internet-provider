package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.exceptions.DbProcessingException;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;
import com.mieshkov.corplan.utils.TariffPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Class for redirecting to the page with tariff catalogue
 * @author Ivan Mieshkov
 */
public class Tariffs implements Command {
    static final Logger LOG = LoggerFactory.getLogger(Tariffs.class);
    private final String[] hasAccess = {StringContainer.CLIENT_ROLE, StringContainer.ADMIN_ROLE};
    private String service;
    private String id;
    private String nameEn;
    private String nameUkr;
    private String price;

    private final TariffsServiceImpl tariffsService;

    public Tariffs(TariffsServiceImpl tariffsService) {
        this.tariffsService = tariffsService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String type = req.getParameter("type");
        String role = (String) req.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);
        service = req.getParameter(StringContainer.TARIFF_SERVICE);
        nameEn = req.getParameter(StringContainer.NAME_EN);
        nameUkr = req.getParameter(StringContainer.NAME_UKR);
        price = req.getParameter(StringContainer.PRICE);
        id = req.getParameter(StringContainer.TARIFF_ID);
        String sortBy = req.getParameter(StringContainer.SORT_BY) == null
                        ? "name_en" : req.getParameter(StringContainer.SORT_BY);
        String order = req.getParameter(StringContainer.ORDER) == null
                        ? "ASC" : req.getParameter(StringContainer.ORDER);

        int currentPage;
        int itemsPerPage;

        currentPage = req.getParameter("page") != null
                ? Integer.parseInt(req.getParameter("page"))
                : 1;
        itemsPerPage = req.getParameter("itemsPerPage") != null
                ? Integer.parseInt(req.getParameter("itemsPerPage"))
                : 5;
        if(type != null) {
            if(type.equals("create")) {
                create();
            }
            if (type.equals("delete")) {
                delete();
            }
            if (type.equals("edit")) {
                edit();
            }
        }

        TariffPage tariffPage;
        try {
            tariffPage = tariffsService.getByService(service, sortBy, order, currentPage, itemsPerPage);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating tariffs : {}", e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/view/errors/error.jsp";
        }

        int totalTariffs = tariffPage.getCount();
        int totalPages = 0;

        totalPages = totalTariffs % itemsPerPage == 0 ? totalTariffs / itemsPerPage : totalTariffs / itemsPerPage + 1;

        int[] itemsPerPageArray = {5, 10, 15};

        req.setAttribute("tariffs", tariffPage.getTariffList());
        req.setAttribute("service", service);
        req.setAttribute("noOfPages", totalPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("itemsPerPage", itemsPerPage);
        req.setAttribute("sortBy", sortBy);
        req.setAttribute("order", order);
        req.setAttribute("itemsPerPageArray", itemsPerPageArray);

        return "/WEB-INF/view/tariffs/" + role + "-tariff.jsp";
    }

    public void create() {
        Double tariffPrice = Double.parseDouble(price);
        tariffsService.tariffCreate(nameUkr, nameEn, tariffPrice, service);
    }

    public void delete() {
        Long tariffId = id.isEmpty() ? 0L : Long.parseLong(id);
        tariffsService.delete(tariffId);
    }

    public void edit() {
        Long tariffId = id.isEmpty() ? 0L : Long.parseLong(id);
        Tariff tariff = new TariffsServiceImpl().getByTariffId(tariffId);
        tariff.setNameEn(nameEn = nameEn.isEmpty() ? tariff.getNameEn() : nameEn);
        tariff.setNameUkr(nameUkr = nameUkr.isEmpty() ? tariff.getNameUkr() : nameUkr);
        tariff.setPrice(price.isEmpty() ? tariff.getPrice() : Double.parseDouble(price));
        tariff.setService(service = service.isEmpty() ? tariff.getService() : service);
        tariffsService.tariffEdit(tariff);
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }

}
