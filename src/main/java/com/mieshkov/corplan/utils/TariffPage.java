package com.mieshkov.corplan.utils;

import com.mieshkov.corplan.model.entities.Tariff;

import java.util.List;

public class TariffPage {
    int count;
    List<Tariff> tariffList;

    public TariffPage(int count, List<Tariff> tariffList) {
        this.count = count;
        this.tariffList = tariffList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }
}
