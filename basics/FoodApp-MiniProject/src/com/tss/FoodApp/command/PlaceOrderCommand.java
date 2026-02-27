package com.tss.FoodApp.command;

import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.service.interfaces.IOrderService;

public class PlaceOrderCommand implements Command {

    private IOrderService orderService;
    private Order order;

    public PlaceOrderCommand(IOrderService service, Order order) {
        this.orderService = service;
        this.order = order;
    }

    @Override
    public void execute() {
        orderService.confirmOrder(order);
        System.out.println("✅ Order placed successfully");
    }

    @Override
    public void undo() {
        orderService.cancelOrder(order.getOrderId());
        System.out.println("↩ Order placement undone");
    }
}