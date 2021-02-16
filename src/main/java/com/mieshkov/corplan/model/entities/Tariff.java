package com.mieshkov.corplan.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class Tariff {
    private Long id;
    private String tariffNameUkr;
    private String tariffNameEn;
    private Double tariffPrice;
    private String tariffService;

    public Tariff(Long id, String tariffNameUkr, String tariffNameEn, Double tariffPrice, String tariffService) {
        this.id = id;
        this.tariffNameUkr = tariffNameUkr;
        this.tariffNameEn = tariffNameEn;
        this.tariffPrice = tariffPrice;
        this.tariffService = tariffService;
    }

    public Tariff(String tariffNameUkr, String tariffNameEn, Double tariffPrice, String tariffService) {
        this.tariffNameUkr = tariffNameUkr;
        this.tariffNameEn = tariffNameEn;
        this.tariffPrice = tariffPrice;
        this.tariffService = tariffService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffNameUkr() {
        return tariffNameUkr;
    }

    public String getTariffNameEn() {
        return tariffNameEn;
    }

    public void setTariffNameUkr(String tariffNameUkr) {
        this.tariffNameUkr = tariffNameUkr;
    }

    public void setTariffNameEn(String tariffNameEn) {
        this.tariffNameEn = tariffNameEn;
    }

    public Double getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(Double tariffPrice) {
        this.tariffPrice = tariffPrice;
    }

    public String getTariffService() {
        return tariffService;
    }

    public void setTariffService(String tariffService) {
        this.tariffService = tariffService;
    }
}
