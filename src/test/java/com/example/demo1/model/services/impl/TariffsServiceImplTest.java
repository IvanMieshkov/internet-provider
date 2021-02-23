package com.example.demo1.model.services.impl;

import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.model.services.impl.TariffsServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.DbSetupTest;

import java.util.List;

/**
 * @author Ivan Mieshkov
 */

public class TariffsServiceImplTest {
    TariffsServiceImpl tariffsService = new TariffsServiceImpl();

    @Before
    public void setUp() {
        DbSetupTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        DbSetupTest.tearDownDataBase();
    }

    @Test
    public void getByTariffId() {
        Tariff tariff = tariffsService.getByTariffId(2L);
        Assert.assertEquals("tv", tariff.getService());
    }

    @Test
    public void getAllByService() {
        List<Tariff> tariffs = tariffsService.getAllByService("internet");
        Assert.assertEquals(1, tariffs.size());
    }

    @Test
    public void tariffCreate() {
        Tariff tariff = new Tariff();
        tariff.setNameUkr("Новий");
        tariff.setNameEn("New Tariff");
        tariff.setPrice(140.99);
        tariff.setService("tv");
        tariffsService.tariffCreate(tariff);
        tariff = tariffsService.getByTariffId(4L);
        Assert.assertEquals("New Tariff", tariff.getNameEn());
    }

    @Test
    public void delete() {
        tariffsService.delete(2L);
        List<Tariff> tariffs = tariffsService.findAll();
        Assert.assertEquals(2, tariffs.size());
    }

}