package com.mieshkov.corplan.controller;

/**
 * @author Ivan Mieshkov
 */

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.controller.command.*;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(MainServlet.class.getSimpleName());
    private Map<String, Command> commands;

    @Override
    public void init() {
        commands = new HashMap<>();
        commands.put("main-page", new MainPage());
        commands.put("login", new Login());
        commands.put("menu", new Menu());
        commands.put("tariffs", new Tariffs());
        commands.put("payment", new Payment());
        commands.put("clients", new UsersList(new UserServiceImpl()));
        commands.put("logout", new Logout());
        commands.put("change-password", new ChangePassword());
        commands.put("change-password-commit", new ChangePasswordCommit());
        commands.put("choose-tariff", new TariffAccepting());
        commands.put("change-status", new StatusProcessing());
        commands.put("add-user", new AddUser());
        commands.put("tariff-edit-form", new TariffEditForm());
        commands.put("tariff-edit", new TariffEdit());
        commands.put("tariff-add", new TariffAdd());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "").replaceAll("\\?*", "");

        String role = (String) request.getSession().getAttribute(StringContainer.USER_LOGGED_ROLE);

        Command command = commands.getOrDefault(path, commands.get("main-page"));
        String page;
        if(command.checkRole(role)) {
            page = command.execute(request);
        } else {
            String message = "Attempt of moving to forbidden page";
            LOGGER.error(message);
            request.setAttribute("errorMessage", message);
            page = "/WEB-INF/errors/error.jsp";
        }
        request.getRequestDispatcher(page).forward(request, response);

    }
}
