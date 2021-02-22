package com.mieshkov.corplan.controller.filter;

import com.mieshkov.corplan.containers.RegexContainer;
import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.dao.UserDao;
import com.mieshkov.corplan.model.dao.UserTariffDao;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.entities.UserTariff;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
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
                checkByRegex(login, StringContainer.LOGIN_INCORRECT);
                checkByRegex(password, StringContainer.PASSWORD_INCORRECT);
            } catch (IncorrectDataInputException e) {
                LOGGER.error("Incorrect input data on login page");
                request.setAttribute("warning", e.getMessage());
                request.getRequestDispatcher(StringContainer.LOGIN_PAGE).forward(request, response);
            }
        }
        long id = 0L;

        if(login!= null) {
             id = Long.parseLong(login);
        }

        final AtomicReference<UserDao> userDao = (AtomicReference<UserDao>) request.getServletContext()
                .getAttribute("userDao");
        final AtomicReference<UserTariffDao> userTariffDao = (AtomicReference<UserTariffDao>) request.getServletContext()
                .getAttribute("userTariffDao");
        final HttpSession session = request.getSession();


        if (Objects.nonNull(session) &&
                Objects.nonNull(session.getAttribute(StringContainer.USER_LOGGED)) &&
                Objects.nonNull(session.getAttribute(StringContainer.USER_LOGGED_ROLE))) {


            filterChain.doFilter(request, response);

        } else if (Objects.nonNull(userDao.get().findById(id))) {
            final User user = userDao.get().findById(id);
            final List<UserTariff> userTariffs = userTariffDao.get().findTariffsByUserId(user.getId());

            if (BCrypt.checkpw(password, user.getPassword())) {
                final String role = user.getRole();

                request.getSession().setAttribute(StringContainer.USER_LOGGED, user);
                request.getSession().setAttribute(StringContainer.USER_LOGGED_ROLE, role);
                request.getSession().setAttribute(StringContainer.USER_TARIFFS, userTariffs);

                if(role.equals(StringContainer.ADMIN_ROLE)) {
                    request.setAttribute(StringContainer.USERS_LIST, new UserServiceImpl().showAllUsers());
                }
                LOGGER.info(role + " authorized");
                request.getRequestDispatcher("/WEB-INF/view/menu/" + role + "-menu.jsp").forward(request, response);
            } else {
                request.setAttribute("warning", StringContainer.LOGIN_PASSWORD_INCORRECT);
                request.getRequestDispatcher(StringContainer.LOGIN_PAGE).forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher(StringContainer.LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private void checkByRegex(String dataToCheck, String warning) throws IncorrectDataInputException {
        if (!dataToCheck.matches(RegexContainer.LOGIN_REGEX) && !dataToCheck.matches(RegexContainer.PASSWORD_REGEX)) {
            throw new IncorrectDataInputException(warning);
        }
    }
}
