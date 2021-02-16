package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Mieshkov
 */
public class MainPage implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return StringContainer.MAIN_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return true;
    }
}
