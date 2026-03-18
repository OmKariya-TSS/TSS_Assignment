package com.tss.FoodAppTest.config;

import com.tss.FoodApp.singleton.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class AppConfigTest {
    private AppConfig  appConfig;

    @BeforeEach
    public void setUp() {
        appConfig = AppConfig.getInstance();
    }
    @Test
    public void testAnotherInstance(){
        AppConfig instance = AppConfig.getInstance();
        assertSame(appConfig,instance,"both references pointing to same instance");
    }

    @Test
    void testDefaultDiscountStrategy(){
        assertEquals(500.0,appConfig.getDiscountThreshold());
        assertEquals(50.0,appConfig.getFlatDiscountAmount());
        assertEquals(10.0,appConfig.getDiscountPercentage());
        assertEquals("FLAT",appConfig.getActiveDiscountType());
    }

    @Test
    void testGettersSetters(){
        appConfig.setDiscountThreshold(5000.0);
        appConfig.setFlatDiscountAmount(500.0);
        appConfig.setDiscountPercentage(30.0);
        appConfig.setActiveDiscountType("PERCENTAGE");

        assertEquals(5000.0,appConfig.getDiscountThreshold());
        assertEquals(500.0,appConfig.getFlatDiscountAmount());
        assertEquals(30.0,appConfig.getDiscountPercentage());
        assertEquals("PERCENTAGE",appConfig.getActiveDiscountType());
    }

}
