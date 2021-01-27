package com.example.demo1.controller;

/**
 * @author Ivan Mieshkov
 */

import com.example.demo1.controller.command.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo1.containers.StringContainer.USER_LOGGED_ROLE;

public class MainServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(MainServlet.class.getSimpleName());
    private Map<String, Command> commands;

    @Override
    public void init() throws ServletException {
        commands = new HashMap<>();
        commands.put("main-page", new MainPage());
        commands.put("login", new Login());
        commands.put("menu", new Menu());
        commands.put("tariffs", new Tariffs());
        commands.put("services", new Services());
        commands.put("clients", new ClientsList());
//        commands.put("logout", new Logout()); //доделать начиная отсюда
//        commands.put("change-password", new ChangePassword());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(".*/", "").replaceAll("\\?*", "");

        String role = (String) request.getSession().getAttribute(USER_LOGGED_ROLE);

        Command command = commands.getOrDefault(path, commands.get("main-page"));
        String page;
        if(command.checkRole(role)) {
            page = command.execute(request);
        } else {
            LOGGER.error("Attempt of moving to forbidden page");
            page = "/WEB-INF/errors/error403.jsp";
        }
        request.getRequestDispatcher(page).forward(request, response);

    }
}
