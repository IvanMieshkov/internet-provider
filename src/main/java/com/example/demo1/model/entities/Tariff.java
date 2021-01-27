package com.example.demo1.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class Tariff {
    private Integer id;
    private String tariffNameUkr;
    private String tariffNameEn;
    private Double tariffPrice;
    private String tariffService;

    public Tariff(Integer id, String tariffNameUkr, String tariffNameEn, Double tariffPrice, String tariffService) {
        this.id = id;
        this.tariffNameUkr = tariffNameUkr;
        this.tariffNameEn = tariffNameEn;
        this.tariffPrice = tariffPrice;
        this.tariffService = tariffService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
