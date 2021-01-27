package com.example.demo1.containers;

import javax.management.remote.rmi._RMIConnection_Stub;

/**
 * @author Ivan Mieshkov
 */
public interface StringContainer {
    String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DATABASE = "jdbc:mysql://localhost:3306/corplan_db?useSSL=false&serverTimezone=UTC"; //add charset=utf8
    String DATABASE_LOGIN = "root";
    String DATABASE_PASSWORD = "root";

    String QUERIES_BUNDLE = "queries";
    String USER_LOGGED = "user";
    String LOCALE_UKR = "ukr";
    String LOCALE_EN = "en";
    String LANGUAGE = "language";
    String USER_LOGGED_ROLE = "role";
    String CLIENT_ROLE = "client";
    String ADMIN_ROLE = "admin";

    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String MAIN_PAGE = "/WEB-INF/view/main-page.jsp";
    String CLIENTS_LIST_PAGE = "/WEB-INF/view/admin-menu/clients-list.jsp";

    String USER_NAME_UKR_INCORRECT = "warn.incorrect.user.name.ukr";
    String USER_NAME_LAT_INCORRECT = "warn.incorrect.user.name.lat";
    String USER_SURNAME_UKR_INCORRECT = "warn.incorrect.user.surname.ukr";
    String USER_SURNAME_LAT_INCORRECT = "warn.incorrect.user.surname.lat";
    String LOGIN_INCORRECT = "warn.incorrect.user.login";
    String PASSWORD_INCORRECT = "warn.incorrect.user.password";
    String LOGIN_PASSWORD_INCORRECT = "warn.incorrect.login.or.password";
    String EMAIL_INCORRECT = "warn.incorrect.user.email";
    String LOGIN_EXISTS = "warn.login.exists";
    String INCORRECT_PASSWORD_WARNING = "warn.incorrect.password";

    String CURRENCY = "currency";

    String INTERNET_SERVICE = "internet";
    String TV_SERVICE = "tv";
    String TELEPHONY_SERVICE = "telephony";
    String PDF = "pdf";
    String TARIFF_SERVICE = "service";
    String SORT_BY = "sortBy";
    String ORDER = "order";
    String NAME_SORTING = "name";
    String PRICE_SORTING = "price";
    String ASC = "asc";
    String DESC = "desc";





}
