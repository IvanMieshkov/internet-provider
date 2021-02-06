package com.example.demo1.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class UserTariff {
    private int id;
    private int userId;
    private int tariffId;
    private String service;
    private Tariff tariff;

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public UserTariff(int userId, int tariffId, String service) {
        this.userId = userId;
        this.tariffId = tariffId;
        this.service = service;
    }

    public UserTariff(int userId, int tariffId) {
        this.userId = userId;
        this.tariffId = tariffId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public UserTariff(int id, int userId, int tariffId, String service, Tariff tariff) {
        this.id = id;
        this.userId = userId;
        this.tariffId = tariffId;
        this.service = service;
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }
}
