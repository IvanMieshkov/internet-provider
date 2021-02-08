package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.CHANGE_PASSWORD_PAGE;
import static com.example.demo1.containers.StringContainer.CLIENT_ROLE;

/**
 * @author Ivan Mieshkov
 */
public class ChangePassword implements Command {
    private String[] hadAccess = {CLIENT_ROLE};

    @Override
    public String execute(HttpServletRequest req) {
        return CHANGE_PASSWORD_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hadAccess).contains(role);
    }
}
