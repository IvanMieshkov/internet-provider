package com.mieshkov.corplan.controller.filter;

import com.mieshkov.corplan.containers.StringContainer;
import com.mieshkov.corplan.model.entities.User;
import com.mieshkov.corplan.model.exceptions.EmailAlreadyExistsException;
import com.mieshkov.corplan.model.exceptions.IncorrectDataInputException;
import com.mieshkov.corplan.model.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mieshkov.corplan.containers.RegexContainer.*;

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

        final String nameEn = request.getParameter(StringContainer.NAME_EN);
        final String nameUkr = request.getParameter(StringContainer.NAME_UKR);
        final String password = request.getParameter(StringContainer.PASSWORD);
        final String email = request.getParameter(StringContainer.EMAIL);
        final String address = request.getParameter(StringContainer.ADDRESS);
        final String phone = request.getParameter(StringContainer.PHONE);

        try {
            checkByRegex(nameEn, LAT_NAME_SURNAME_REGEX, StringContainer.USER_NAME_LAT_INCORRECT);
            checkByRegex(nameUkr, UKR_NAME_SURNAME_REGEX, StringContainer.USER_NAME_UKR_INCORRECT);
            checkByRegex(password, PASSWORD_REGEX, StringContainer.PASSWORD_INCORRECT);
            checkByRegex(email, EMAIL_REGEX, StringContainer.EMAIL_INCORRECT);
            checkByRegex(phone, PHONE_NUMBER_REGEX, StringContainer.PHONE_NUMBER_INCORRECT);

        } catch (IncorrectDataInputException e) {
            request.setAttribute("warning", e.getMessage());
            LOGGER.error("Incorrect input data on registration page");
            request.getRequestDispatcher(StringContainer.ADD_USER_PAGE).forward(request, response);
            return;
        }

        final String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(nameEn, nameUkr, hashPassword, email, address, phone, StringContainer.CLIENT_ROLE);

        try {
            new UserServiceImpl().registerUser(user);
        } catch (EmailAlreadyExistsException e) {
            request.setAttribute("warning", StringContainer.LOGIN_EXISTS);
            LOGGER.warn("Attempt to register already registered user");
            request.getRequestDispatcher(StringContainer.ADD_USER_PAGE).forward(request, response);
            return;
        }
        request.setAttribute(StringContainer.USERS_LIST, new UserServiceImpl().showAllUsers());
        request.getRequestDispatcher(StringContainer.ADMIN_MENU).forward(request, response);
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
