package com.mieshkov.corplan.containers;

/**
 * @author Ivan Mieshkov
 */
public interface StringContainer {

    String USER_LOGGED = "user";
    String LOCALE_UKR = "ukr";
    String LOCALE_EN = "en";
    String LANGUAGE = "language";
    String USER_LOGGED_ROLE = "role";
    String CLIENT_ROLE = "client";
    String ADMIN_ROLE = "admin";
    String USERS_LIST = "clients";

    String LOGIN_PAGE = "/WEB-INF/view/login.jsp";
    String MAIN_PAGE = "/WEB-INF/view/main-page.jsp";
    String USERS_LIST_PAGE = "/WEB-INF/view/admin-processing/users-list.jsp";
    String CLIENT_MENU = "/WEB-INF/view/menu/client-menu.jsp";
    String ADMIN_MENU = "/WEB-INF/view/menu/admin-menu.jsp";
    String CHANGE_PASSWORD_PAGE = "/WEB-INF/view/user-processing/change-password-page.jsp";
    String ADD_USER_PAGE = "/WEB-INF/view/admin-processing/add-user.jsp";
    String TARIFF_EDIT_ADD_PAGE = "/WEB-INF/view/admin-processing/tariff-edit-add-form.jsp";

    String USER_NAME_UKR_INCORRECT = "warn.incorrect.user.name.ukr";
    String USER_NAME_LAT_INCORRECT = "warn.incorrect.user.name.lat";
    String LOGIN_INCORRECT = "warn.incorrect.user.login";
    String PASSWORD_INCORRECT = "warn.incorrect.user.password";
    String LOGIN_PASSWORD_INCORRECT = "warn.incorrect.login.or.password";
    String EMAIL_INCORRECT = "warn.incorrect.user.email";
    String LOGIN_EXISTS = "warn.login.exists";
    String INCORRECT_PASSWORD_WARNING = "warn.incorrect.password";
    String PHONE_NUMBER_INCORRECT = "warn.incorrect.phone";
    String PAYMENT_INCORRECT = "warn.incorrect.payment";

    String CURRENCY = "currency";
    String PAYMENT = "payment";

    String TARIFF_SERVICE = "service";
    String SORT_BY = "sortBy";
    String ORDER = "order";

    String LOGIN = "login";
    String NAME_EN = "nameEn";
    String NAME_UKR = "nameUkr";
    String PASSWORD = "password";
    String EMAIL = "email";
    String ADDRESS = "address";
    String PHONE = "phone";
    String SELECTED_TARIFF = "selectedTariff";
    String USER_TARIFFS = "userTariffs";
    String ACTIVE = "active";
    String USER_ID = "id";
    String TARIFF_ID = "id";
    String PRICE = "price";
}
