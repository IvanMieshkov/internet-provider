package com.example.demo1.containers;

/**
 * @author Ivan Mieshkov
 */
public interface RegexContainer {
    String UKR_NAME_SURNAME_REGEX = "[[А-ЯҐІЇЄ]&&[^ЁЫЭЪ]][[а-яґєії\\']&&[^ёыэъ]]{1,18}[[а-яґєії]&&[^ёыэъ]]";
    String LAT_NAME_SURNAME_REGEX = "[A-Z][a-z]+";
    String LOGIN_PASSWORD_REGEX = "[A-Za-z0-9_@]{6,20}";
    String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
}
