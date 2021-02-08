package com.example.demo1.controller.filter;

import com.example.demo1.model.dao.UserDao;
import com.example.demo1.model.dao.UserTariffDao;
import com.example.demo1.model.entities.User;
import com.example.demo1.model.entities.UserTariff;
import com.example.demo1.model.exceptions.IncorrectDataInputException;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.demo1.containers.RegexContainer.LOGIN_REGEX;
import static com.example.demo1.containers.RegexContainer.PASSWORD_REGEX;
import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class AuthenticationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        if (Objects.nonNull(login) && Objects.nonNull(password)) {
            try {
                checkByRegex(login, LOGIN_INCORRECT);
                checkByRegex(password, PASSWORD_INCORRECT);
            } catch (IncorrectDataInputException e) {
                LOGGER.error("Incorrect input data on login page");
                request.setAttribute("warning", e.getMessage());
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        }

        final AtomicReference<UserDao> userDao = (AtomicReference<UserDao>) request.getServletContext()
                .getAttribute("userDao");
        final AtomicReference<UserTariffDao> userTariffDao = (AtomicReference<UserTariffDao>) request.getServletContext()
                .getAttribute("userTariffDao");
        final HttpSession session = request.getSession();


        if (Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute(USER_LOGGED)) &&
                Objects.nonNull(session.getAttribute(USER_LOGGED_ROLE))) {


            filterChain.doFilter(request, response);

        } else if (Objects.nonNull(userDao.get().findByLogin(login))) {
            final User user = userDao.get().findByLogin(login);
            final List<UserTariff> userTariffs = userTariffDao.get().findTariffsByUserId(user.getId());

            if (BCrypt.checkpw(password, user.getPassword())) {
                final String role = user.getRole();

                request.getSession().setAttribute(USER_LOGGED, user);
                request.getSession().setAttribute(USER_LOGGED_ROLE, role);
                request.getSession().setAttribute(USER_TARIFFS, userTariffs);

                LOGGER.info(role + " authorized");
                request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
            } else {
                request.setAttribute("warning", LOGIN_PASSWORD_INCORRECT);
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private void checkByRegex(String dataToCheck, String warning) throws IncorrectDataInputException {
        if (!dataToCheck.matches(LOGIN_REGEX) && !dataToCheck.matches(PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(warning);
        }
    }
}
