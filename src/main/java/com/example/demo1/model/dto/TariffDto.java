package com.example.demo1.model.dto;

import com.example.demo1.model.entities.Tariff;

/**
 * @author Ivan Mieshkov
 */
public class TariffDto {
    private Tariff tariff;
    private String name;

    public TariffDto(Tariff tariff) {
        this.tariff = tariff;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
