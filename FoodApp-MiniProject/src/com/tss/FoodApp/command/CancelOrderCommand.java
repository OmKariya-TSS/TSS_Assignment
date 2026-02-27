package com.tss.FoodApp.command;

import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.service.interfaces.IOrderService;

public class CancelOrderCommand implements Command {

    private IOrderService orderService;
    private int orderId;
    private Order cancelledOrder;  // save for undo

    public CancelOrderCommand(IOrderService service, int orderId) {
        this.orderService = service;
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        cancelledOrder = orderService.getOrderById(orderId);
        boolean ans= orderService.cancelOrder(orderId);
        if(ans) {
            System.out.println("❌ Order #" + orderId + " cancelled successfully");
        }
    }

    @Override
    public void undo() {
        if (cancelledOrder != null) {
            orderService.restoreOrder(cancelledOrder);
            System.out.println("↩ Order #" + orderId + " restored successfully");
        } else {
            System.out.println("⚠ No order to restore!");
        }
    }
}