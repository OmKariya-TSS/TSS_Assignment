package com.tss.FoodAppTest.service;

import com.tss.FoodApp.enums.MenuCategory;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.repository.interfaces.IInvoiceRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.service.implementations.InvoiceServiceImpl;
import com.tss.FoodApp.service.interfaces.IInvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InvoiceServiceTest {
    @Mock
    private IOrderRepository orderRepo;
    @Mock
    private IInvoiceRepository invoiceRepo;
    private IInvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceService = new InvoiceServiceImpl(orderRepo, invoiceRepo);
    }
    @Test
    void testGenerateInvoice(){
        Order order = new Order();
        order.setOrderId(1);
        order.setItems(new ArrayList<>());
        Customer customer = new Customer(
                1,"om kariya","customer@gmail.com",
                "1234567890","1234567890","rajokt"
        );
        order.setCustomer(customer);
        MenuItem item1 = new MenuItem(
                1,"menuitem",500, MenuCategory.MAIN,"description"
        );
        OrderItem orderItem1 = new OrderItem(item1,1);
        order.addItem(orderItem1);
        order.assignAgent(
                new DeliveryAgent(1,"AGENT","1111111111","agent123")
        );
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        when(invoiceRepo.findById(1)).thenReturn(Optional.empty());
        Invoice invoice = invoiceService.generateInvoice(order);
        assertNotNull(invoice);
    }

    @Test
    void testGetInvoiceByOrderId(){
        Order order = new Order();
        order.setOrderId(1);
        order.setItems(new ArrayList<>());
        Customer customer = new Customer(
                1,"om kariya","customer@gmail.com",
                "1234567890","1234567890","rajokt"
        );
        order.setCustomer(customer);
        MenuItem item1 = new MenuItem(
                1,"menuitem",500, MenuCategory.MAIN,"description"
        );
        OrderItem orderItem1 = new OrderItem(item1,1);
        order.addItem(orderItem1);
        order.assignAgent(
                new DeliveryAgent(1,"AGENT","1111111111","agent123")
        );
        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        Invoice invoice = invoiceService.generateInvoice(order);
        when(invoiceRepo.findByOrderId(1)).thenReturn(Optional.of(invoice));
        Invoice result = invoiceService.getInvoiceByOrderId(1);
        assertEquals(invoice, result);
    }
}
