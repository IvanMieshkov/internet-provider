package com.mieshkov.corplan.controller.command;

import com.mieshkov.corplan.containers.StringContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Ivan Mieshkov
 */
public class ChangePassword implements Command {
    private final String[] hadAccess = {StringContainer.CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        return StringContainer.CHANGE_PASSWORD_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
