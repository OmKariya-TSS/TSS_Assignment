package com.tss.FoodAppV4.service.interfaces;

import com.tss.FoodAppV4.model.Invoice;
import com.tss.FoodAppV4.model.Order;

public interface IInvoiceService {


    Invoice generateInvoice(Order order);


    void printInvoice(int orderId);


    Invoice getInvoiceByOrderId(int orderId);
}