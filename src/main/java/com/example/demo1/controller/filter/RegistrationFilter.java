package com.example.demo1.controller.filter;

import com.example.demo1.model.entities.User;
import com.example.demo1.model.exceptions.IncorrectDataInputException;
import com.example.demo1.model.exceptions.LoginAlreadyExistsException;
import com.example.demo1.model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo1.containers.RegexContainer.*;
import static com.example.demo1.containers.StringContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class RegistrationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(RegistrationFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter(LOGIN);
        final String nameEn = request.getParameter(NAME_EN);
        final String nameUkr = request.getParameter(NAME_UKR);
        final String password = request.getParameter(PASSWORD);
        final String email = request.getParameter(EMAIL);
        final String address = request.getParameter(ADDRESS);
        final String phone = request.getParameter(PHONE);

        try {
            checkByRegex(login, LOGIN_REGEX, LOGIN_INCORRECT);
            checkByRegex(nameEn, LAT_NAME_SURNAME_REGEX, USER_NAME_LAT_INCORRECT);
            checkByRegex(nameUkr, UKR_NAME_SURNAME_REGEX, USER_NAME_UKR_INCORRECT);
            checkByRegex(password, PASSWORD_REGEX, PASSWORD_INCORRECT);
            checkByRegex(email, EMAIL_REGEX, EMAIL_INCORRECT);
            checkByRegex(phone, PHONE_NUMBER_REGEX, PHONE_NUMBER_INCORRECT);

        } catch (IncorrectDataInputException e) {
            request.setAttribute("warning", e.getMessage());
            LOGGER.error("Incorrect input data on registration page");
            request.getRequestDispatcher(ADD_USER_PAGE).forward(request, response);
        }

        final String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(login, nameEn, nameUkr, hashPassword, email, address, phone, CLIENT_ROLE);

        try {
            new UserServiceImpl().registerUser(user);
        } catch (LoginAlreadyExistsException e) {
            request.setAttribute("warning", LOGIN_EXISTS);
            LOGGER.warn("Attempt to register already registered user");
            request.getRequestDispatcher(ADD_USER_PAGE).forward(request, response);
        }
        request.setAttribute(USERS_LIST, new UserServiceImpl().showAllUsers());
        request.getRequestDispatcher(USERS_LIST_PAGE).forward(request, response);
    }

    @Override
    public void destroy() {

    }

    private void checkByRegex(String dataToCheck, String regex, String warning) throws IncorrectDataInputException {
        if (!dataToCheck.matches(regex)) {
            throw new IncorrectDataInputException(warning);
        }
    }

}
