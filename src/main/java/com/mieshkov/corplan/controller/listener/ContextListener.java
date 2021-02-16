package com.mieshkov.corplan.controller.listener;

import com.mieshkov.corplan.model.dao.DaoFactory;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.dao.UserTariffDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ivan Mieshkov
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        AtomicReference<UserDao> userDao = new AtomicReference<>(DaoFactory.getInstance().createUserDao());
        AtomicReference<UserTariffDao> userTariffDao = new AtomicReference<>(DaoFactory.getInstance().createUserTariffDao());

        final ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("userDao", userDao);
        servletContext.setAttribute("userTariffDao", userTariffDao);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
