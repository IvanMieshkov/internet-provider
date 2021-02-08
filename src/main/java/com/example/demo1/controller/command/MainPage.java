package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;

import static com.example.demo1.containers.StringContainer.MAIN_PAGE;
import static com.example.demo1.containers.StringContainer.USER_LOGGED_ROLE;

/**
 * @author Ivan Mieshkov
 */
public class MainPage implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}
