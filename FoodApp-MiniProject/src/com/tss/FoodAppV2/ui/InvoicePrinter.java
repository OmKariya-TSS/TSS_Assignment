package com.tss.FoodAppV2.ui;

import com.tss.FoodAppV2.model.Invoice;
import com.tss.FoodAppV2.model.OrderItem;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InvoicePrinter {

    private final Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null.");
        }
        this.invoice = invoice;
    }

    public void print() {
        try {
            validateInvoice();
            System.out.println(buildHeader());
            System.out.println(buildItemsSection());
            System.out.println(buildTotalsSection());
            System.out.println(buildDeliverySection());
            System.out.println(buildFooter());
        } catch (RuntimeException e) {
            System.out.println("❌ Unable to print invoice: " + e.getMessage());
        }
    }

    private void validateInvoice() {
        if (invoice.getCustomer() == null)
            throw new IllegalStateException("Invoice customer missing.");

        if (invoice.getRestaurant() == null)
            throw new IllegalStateException("Invoice restaurant missing.");

        if (invoice.getItems() == null || invoice.getItems().isEmpty())
            throw new IllegalStateException("Invoice has no items.");
    }

    private String buildHeader() {
        return "\n🧾 INVOICE #" + invoice.getInvoiceId() +
                "\nDate: " + Objects.requireNonNull(invoice.getGeneratedAt(),
                        "Invoice date missing")
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) +
                "\nCustomer: " + invoice.getCustomer().getName() +
                "\nRestaurant: " + invoice.getRestaurant().getName() +
                "\n---------------------------------------------";
    }

    private String buildItemsSection() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-25s %5s %10s\n", "Qty", "Item", "Price", "Total"));
        sb.append("---------------------------------------------\n");

        for (OrderItem item : invoice.getItems()) {
            if (item == null || item.getMenuItem() == null) {
                throw new IllegalStateException("Invalid item found in invoice.");
            }

            sb.append(String.format("%-5d %-25s %5.2f %10.2f\n",
                    item.getQuantity(),
                    item.getMenuItem().getName(),
                    item.getMenuItem().getPrice(),
                    item.getItemTotal()));
        }

        sb.append("---------------------------------------------");
        return sb.toString();
    }

    private String buildTotalsSection() {
        return String.format(
                "\nSubtotal: ₹%.2f\nDiscount: ₹%.2f\nFinal Total: ₹%.2f\nPayment Mode: %s\n",
                invoice.getSubtotal(),
                invoice.getDiscountApplied(),
                invoice.getFinalAmount(),
                invoice.getPaymentMethod() != null
                        ? invoice.getPaymentMethod()
                        : "Not specified"
        );
    }

    private String buildDeliverySection() {
        return "Delivery Agent: " +
                (invoice.getAgent() != null
                        ? invoice.getAgent().getName()
                        : "Not assigned") +
                "\n---------------------------------------------";
    }

    private String buildFooter() {
        return "🙏 Thank you for ordering from " +
                invoice.getRestaurant().getName() + "!";
    }
}