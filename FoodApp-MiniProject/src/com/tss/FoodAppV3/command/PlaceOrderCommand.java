package com.tss.FoodAppV3.command;

import com.tss.FoodAppV3.model.Order;
import com.tss.FoodAppV3.service.interfaces.IOrderService;

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