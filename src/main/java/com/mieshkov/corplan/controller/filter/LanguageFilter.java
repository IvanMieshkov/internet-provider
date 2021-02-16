package com.mieshkov.corplan.controller.filter;

import com.mieshkov.corplan.containers.StringContainer;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ivan Mieshkov
 */
public class LanguageFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LanguageFilter.class.getSimpleName());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String language = (String) request.getSession().getAttribute(StringContainer.LANGUAGE);
        String service = (String) request.getAttribute(StringContainer.TARIFF_SERVICE);

        request.getSession().setAttribute(StringContainer.LANGUAGE, language.equals(StringContainer.LOCALE_UKR) ? StringContainer.LOCALE_EN : StringContainer.LOCALE_UKR);
        request.setAttribute(StringContainer.TARIFF_SERVICE, service);

        LOGGER.info("Language changed to " + request.getSession().getAttribute(StringContainer.LANGUAGE));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
