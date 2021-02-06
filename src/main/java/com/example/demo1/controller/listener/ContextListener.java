package com.example.demo1.controller.listener;

import com.example.demo1.model.dao.DaoFactory;
import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.dao.UserTariffDao;
import org.apache.log4j.Logger;

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

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class.getSimpleName());


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
