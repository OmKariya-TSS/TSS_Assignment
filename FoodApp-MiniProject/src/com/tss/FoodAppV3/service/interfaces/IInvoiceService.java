package com.tss.FoodAppV3.service.interfaces;

import com.tss.FoodAppV3.model.Invoice;
import com.tss.FoodAppV3.model.Order;

public interface IInvoiceService {


    Invoice generateInvoice(Order order);


    void printInvoice(int orderId);


    Invoice getInvoiceByOrderId(int orderId);
}