package com.example.demo1.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class Service {
    private int Id;
    private String serviceNameUkr;
    private String serviceNameEn;

    public Service(int Id, String serviceNameUkr, String serviceNameEn) {
        this.Id = Id;
        this.serviceNameUkr = serviceNameUkr;
        this.serviceNameEn = serviceNameEn;
    }

    public int getId() {
        return Id;
    }

    public void setServiceId(int serviceId) {
        this.Id = serviceId;
    }

    public String getServiceNameUkr() {
        return serviceNameUkr;
    }

    public void setServiceNameUkr(String serviceNameUkr) {
        this.serviceNameUkr = serviceNameUkr;
    }

    public String getServiceNameEn() {
        return serviceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }
}
