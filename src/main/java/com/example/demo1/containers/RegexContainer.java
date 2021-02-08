package com.example.demo1.containers;

/**
 * @author Ivan Mieshkov
 */
public interface RegexContainer {
    String UKR_NAME_SURNAME_REGEX = "[[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]{2,}" +
                                    "(?: [[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]*){0,2}$";
    String LAT_NAME_SURNAME_REGEX = "^[A-Z][a-zA-Z]{2,}(?: [A-Z][a-zA-Z]*){0,2}$";
    String LOGIN_REGEX = "[0-9]{6}";
    String PASSWORD_REGEX = "[A-Za-z0-9_@]{6,20}";
    String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    String PHONE_NUMBER_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" +
                                "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
}
