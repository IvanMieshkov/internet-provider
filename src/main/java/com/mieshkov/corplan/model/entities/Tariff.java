package com.mieshkov.corplan.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class Tariff {
    private Long id;
    private String nameUkr;
    private String nameEn;
    private Double price;
    private String service;

    public Tariff(Long id, String nameUkr, String nameEn, Double price, String service) {
        this.id = id;
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
        this.price = price;
        this.service = service;
    }

    public Tariff(String nameUkr, String nameEn, Double price, String service) {
        this.nameUkr = nameUkr;
        this.nameEn = nameEn;
        this.price = price;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
