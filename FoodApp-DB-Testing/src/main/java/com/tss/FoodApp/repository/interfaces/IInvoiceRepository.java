package com.tss.FoodApp.repository.interfaces;


import com.tss.FoodApp.model.Invoice;
import java.util.List;
import java.util.Optional;

public interface IInvoiceRepository {

    void save(Invoice invoice);

    Optional<Invoice> findById(int invoiceId);

    List<Invoice> findAll();

    List<Invoice> findByCustomer(int customerId);

    List<Invoice> findByRestaurant(int restaurantId);

    void delete(int invoiceId);

    Optional<Invoice> findByOrderId(int orderId);

    void update(Invoice invoice);
}
