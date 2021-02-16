package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class AddUser implements Command {
    private String[] hasAccess = {StringContainer.ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        return StringContainer.ADD_USER_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}