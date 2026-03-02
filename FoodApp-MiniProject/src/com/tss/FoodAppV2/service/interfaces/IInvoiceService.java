package com.tss.FoodAppV2.service.interfaces;

import com.tss.FoodAppV2.model.Invoice;
import com.tss.FoodAppV2.model.Order;

public interface IInvoiceService {


    Invoice generateInvoice(Order order);


    void printInvoice(int orderId);


    Invoice getInvoiceByOrderId(int orderId);
}