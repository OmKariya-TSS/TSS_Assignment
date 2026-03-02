package com.tss.FoodAppV2.service.implementations;

import com.tss.FoodAppV2.builder.InvoiceBuilder;
import com.tss.FoodAppV2.exceptions.InvoiceGenerationException;
import com.tss.FoodAppV2.exceptions.InvoiceNotFoundException;
import com.tss.FoodAppV2.model.Invoice;
import com.tss.FoodAppV2.model.Order;
import com.tss.FoodAppV2.repository.interfaces.IOrderRepository;
import com.tss.FoodAppV2.service.interfaces.IInvoiceService;

import java.util.HashMap;
import java.util.Map;

public class InvoiceServiceImpl implements IInvoiceService {

    private final IOrderRepository orderRepo;
    private final Map<Integer, Invoice> invoiceStore = new HashMap<>();

    public InvoiceServiceImpl(IOrderRepository orderRepo) {
        if (orderRepo == null) {
            throw new IllegalArgumentException("Order repository cannot be null");
        }
        this.orderRepo = orderRepo;
    }

    @Override
    public Invoice generateInvoice(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getOrderId() <= 0) {
            throw new InvoiceGenerationException("Invalid order ID");
        }

        if (invoiceStore.containsKey(order.getOrderId())) {
            throw new InvoiceGenerationException(
                    "Invoice already generated for order #" + order.getOrderId()
            );
        }

        orderRepo.findById(order.getOrderId())
                .orElseThrow(() ->
                        new InvoiceGenerationException(
                                "Order not found in repository for ID: "
                                        + order.getOrderId()
                        )
                );

        Invoice invoice = new InvoiceBuilder()
                .setOrder(order)
                .setGeneratedAt()
                .calculateTotals()
                .build();

        invoiceStore.put(order.getOrderId(), invoice);

        System.out.println("🧾 Invoice generated for order #" + order.getOrderId());

        return invoice;
    }

    @Override
    public void printInvoice(int orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Invoice invoice = getInvoiceByOrderId(orderId);

        invoice.printInvoice();
    }

    @Override
    public Invoice getInvoiceByOrderId(int orderId) {

        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order ID");
        }

        Invoice invoice = invoiceStore.get(orderId);

        if (invoice == null) {
            throw new InvoiceNotFoundException(
                    "Invoice not found for order #" + orderId
            );
        }

        return invoice;
    }
}