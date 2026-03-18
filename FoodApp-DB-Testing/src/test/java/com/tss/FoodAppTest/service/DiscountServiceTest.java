package com.tss.FoodAppTest.service;

import com.tss.FoodApp.discount.*;
import com.tss.FoodApp.repository.interfaces.IDiscountConfigRepo;
import com.tss.FoodApp.service.implementations.DiscountServiceImpl;
import com.tss.FoodApp.service.interfaces.IDiscountService;
import com.tss.FoodApp.singleton.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DiscountServiceTest {
    @Mock
    private DiscountContext discountContext;
    @Mock
    private IDiscountConfigRepo discountConfigRepo;
    private IDiscountService discountService;
    private AppConfig appConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        discountService = new DiscountServiceImpl(discountContext,discountConfigRepo);
        appConfig = AppConfig.getInstance();
    }

    @Test
    void testSetFlatDiscount() {
        discountService.setFlatDiscount(100.0,5000.0);
        assertEquals(100,appConfig.getFlatDiscountAmount());
        assertEquals(5000.0,appConfig.getDiscountThreshold());
        assertEquals("FLAT",appConfig.getActiveDiscountType());
        //verifying flat discount was called or not using set strategy
        ArgumentCaptor<FlatDiscountStrategy> captor = ArgumentCaptor.forClass(FlatDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();
    }

    @Test
    void setPercentageDiscount() {
        discountService.setPercentageDiscount(100.0,5000.0);
        assertEquals(100.0,appConfig.getDiscountPercentage());
        assertEquals(5000.0,appConfig.getDiscountThreshold());
        assertEquals("PERCENTAGE",appConfig.getActiveDiscountType());
        ArgumentCaptor<PercentageDiscountStrategy> captor = ArgumentCaptor.forClass(PercentageDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();

    }

    @Test
    void testNoDiscount(){
        discountService.setNoDiscount();
        assertEquals("NONE",appConfig.getActiveDiscountType());
        ArgumentCaptor<NoDiscountStrategy> captor = ArgumentCaptor.forClass(NoDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();
    }

    @Test
    void testUpdateThresholdFlat(){
        appConfig.setActiveDiscountType("FLAT");
        appConfig.setFlatDiscountAmount(100.0);
        discountService.updateThreshold(1000.0);
        assertEquals(1000.0,appConfig.getDiscountThreshold());
        assertEquals("FLAT",appConfig.getActiveDiscountType());
        ArgumentCaptor<FlatDiscountStrategy> captor = ArgumentCaptor.forClass(FlatDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();
    }

    @Test
    void testUpdateThresholdPercentage(){
        appConfig.setActiveDiscountType("PERCENTAGE");
        appConfig.setDiscountPercentage(50.0);
        discountService.updateThreshold(5000.0);
        assertEquals(50.0,appConfig.getDiscountPercentage());
        assertEquals("PERCENTAGE",appConfig.getActiveDiscountType());
        ArgumentCaptor<PercentageDiscountStrategy> captor = ArgumentCaptor.forClass(PercentageDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();
    }

    @Test
    void testUpdateThresholdNoDiscount(){
        appConfig.setActiveDiscountType("NONE");
        discountService.updateThreshold(5000.0);
        assertEquals(5000.0,appConfig.getDiscountThreshold());
        assertEquals("NONE",appConfig.getActiveDiscountType());
        ArgumentCaptor<NoDiscountStrategy> captor = ArgumentCaptor.forClass(NoDiscountStrategy.class);
        verify(discountContext).setStrategy(captor.capture());
        verify(discountConfigRepo).update();
    }
}
