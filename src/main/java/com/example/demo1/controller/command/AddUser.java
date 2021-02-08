package com.example.demo1.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.example.demo1.containers.StringContainer.ADD_USER_PAGE;
import static com.example.demo1.containers.StringContainer.ADMIN_ROLE;

/**
 * @author Ivan Mieshkov
 */
public class AddUser implements Command {
    private String[] hasAccess = {ADMIN_ROLE};
    @Override
    public String execute(HttpServletRequest req) {
        return ADD_USER_PAGE;
    }

    @Override
    public boolean checkRole(String role) {
        return Arrays.asList(hasAccess).contains(role);
    }
}