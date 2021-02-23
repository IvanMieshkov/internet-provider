package com.mieshkov.corplan.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class User {
    private Long id;
    private String nameEn;
    private String nameUkr;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private Double balance;
    private String role;
    private boolean active;

    public User() {}

    public User(String nameEn, String nameUkr, String password, String email, String address, String phoneNumber, String role) {
        this.nameEn = nameEn;
        this.nameUkr = nameUkr;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
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
