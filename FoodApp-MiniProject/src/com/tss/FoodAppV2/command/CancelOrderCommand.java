package com.tss.FoodAppV2.command;

import com.tss.FoodAppV2.model.Order;
import com.tss.FoodAppV2.service.interfaces.IOrderService;

public class CancelOrderCommand implements Command {

    private IOrderService orderService;
    private int orderId;
    private Order cancelledOrder;

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