package com.mieshkov.corplan.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Mieshkov
 */
public interface Command {
    String execute(HttpServletRequest req);
    boolean checkRole(String role);

}
