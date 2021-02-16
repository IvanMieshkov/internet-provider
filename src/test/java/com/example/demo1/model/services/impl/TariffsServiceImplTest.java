package com.example.demo1.model.services.impl;

import com.example.demo1.model.dto.TariffDto;
import com.example.demo1.model.entities.Tariff;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.DbSetupTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getAllTariffs() {
        List<TariffDto> tariffs = tariffsService.getAllTariffs("en_EN");
        Assert.assertEquals(3, tariffs.size());
    }

    @Test
    public void getByService() {
        List<TariffDto> tariffs = tariffsService.getByService("internet", "en_EN");
        Assert.assertEquals(1, tariffs.size());
    }

}