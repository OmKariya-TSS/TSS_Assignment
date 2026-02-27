package com.tss.FoodApp.service.interfaces;

import com.tss.FoodApp.model.Invoice;
import com.tss.FoodApp.model.Order;

public interface IInvoiceService {


    Invoice generateInvoice(Order order);


    void printInvoice(int orderId);


    Invoice getInvoiceByOrderId(int orderId);
}