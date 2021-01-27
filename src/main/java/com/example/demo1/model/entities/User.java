package com.example.demo1.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class User {
    private Integer id;
    private String login;
    private String fullName;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private Double balance;
    private String role;
    private Boolean active;

    public User() {
    }

    public User(Integer id, String login, String fullName, String password,
                String email, String address, String phoneNumber, Double balance, String role, Boolean active) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.role = role;
        this.active = active;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

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
