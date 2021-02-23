package com.mieshkov.corplan.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class UserTariff {
    private Long id;
    private Long userId;
    private Long tariffId;
    private String service;
    private Tariff tariff;

    public UserTariff() {
    }

    public UserTariff(Long userId, Long tariffId, String service) {
        this.userId = userId;
        this.tariffId = tariffId;
        this.service = service;
    }

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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }
}
