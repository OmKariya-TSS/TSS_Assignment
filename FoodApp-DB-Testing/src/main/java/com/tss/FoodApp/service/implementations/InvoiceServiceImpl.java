package com.tss.FoodApp.service.implementations;

import com.tss.FoodApp.builder.InvoiceBuilder;
import com.tss.FoodApp.exceptions.InvoiceGenerationException;
import com.tss.FoodApp.exceptions.InvoiceNotFoundException;
import com.tss.FoodApp.model.Invoice;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.repository.interfaces.IInvoiceRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.service.interfaces.IInvoiceService;

public class InvoiceServiceImpl implements IInvoiceService {

    private final IOrderRepository orderRepo;
    private final IInvoiceRepository invoiceRepo;

    public InvoiceServiceImpl(IOrderRepository orderRepo, IInvoiceRepository invoiceRepo) {
        if (orderRepo == null || invoiceRepo == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.orderRepo = orderRepo;
        this.invoiceRepo = invoiceRepo;
    }


    @Override
    public Invoice generateInvoice(Order order) {

        if (order == null) throw new IllegalArgumentException("Order cannot be null");
        if (order.getOrderId() <= 0) throw new InvoiceGenerationException("Invalid order ID");

        orderRepo.findById(order.getOrderId())
                .orElseThrow(() -> new InvoiceGenerationException(
                        "Order not found for ID: " + order.getOrderId()));

        if (invoiceRepo.findByOrderId(order.getOrderId()).isPresent()) {
            throw new InvoiceGenerationException(
                    "Invoice already generated for order #" + order.getOrderId());
        }

        Invoice invoice = new InvoiceBuilder()
                .setOrder(order)
                .setGeneratedAt()
                .calculateTotals()
                .build();

        invoiceRepo.save(invoice);

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

        if (orderId <= 0) throw new IllegalArgumentException("Invalid order ID");

        return invoiceRepo.findByOrderId(orderId)
                .orElseThrow(() -> new InvoiceNotFoundException(
                        "Invoice not found for order #" + orderId));
    }
}