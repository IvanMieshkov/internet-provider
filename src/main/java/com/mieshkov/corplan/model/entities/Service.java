package com.mieshkov.corplan.model.entities;

/**
 * @author Ivan Mieshkov
 */
public class Service {
    private Long Id;
    private String serviceNameUkr;
    private String serviceNameEn;

    public Service(Long Id, String serviceNameUkr, String serviceNameEn) {
        this.Id = Id;
        this.serviceNameUkr = serviceNameUkr;
        this.serviceNameEn = serviceNameEn;
    }

    public Long getId() {
        return Id;
    }

    public void setServiceId(Long serviceId) {
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
