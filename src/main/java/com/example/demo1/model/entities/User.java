package com.example.demo1.model.entities;

import com.example.demo1.model.dao.TariffDao;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */
public class User {
    private Integer id;
    private String login;
    private String fullNameEn;
    private String fullNameUkr;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private Double balance;
    private String role;
    private Boolean active;

    public User() {
    }

    public User(Integer id, String login, String fullNameEn, String fullNameUkr, String password,
                String email, String address, String phoneNumber, Double balance, String role, Boolean active) {
        this.id = id;
        this.login = login;
        this.fullNameEn = fullNameEn;
        this.fullNameUkr = fullNameUkr;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.role = role;
        this.active = active;
    }

    public User(String login, String fullNameEn, String fullNameUkr, String password, String email, String address, String phoneNumber, String role) {
        this.login = login;
        this.fullNameEn = fullNameEn;
        this.fullNameUkr = fullNameUkr;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getFullNameUkr() {
        return fullNameUkr;
    }

    public void setFullNameUkr(String fullNameUkr) {
        this.fullNameUkr = fullNameUkr;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public Boolean getActive() {return active; }

    public void setActive(Boolean active) { this.active = active; }

    public Double getBalance() { return balance; }

    public void setBalance(Double balance) { this.balance = balance; }

}
