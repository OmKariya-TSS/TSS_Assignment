package com.tss.FoodAppTest.service;

import com.tss.FoodApp.discount.DiscountContext;
import com.tss.FoodApp.enums.MenuCategory;
import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.enums.PaymentStatus;
import com.tss.FoodApp.exceptions.InvalidOrderOperationException;
import com.tss.FoodApp.model.*;
import com.tss.FoodApp.observer.OrderEventManager;
import com.tss.FoodApp.repository.interfaces.IDeliveryAgentRepository;
import com.tss.FoodApp.repository.interfaces.IMenuItemRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.implementations.OrderServiceImpl;
import com.tss.FoodApp.service.interfaces.IDeliveryService;
import com.tss.FoodApp.service.interfaces.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    IOrderRepository orderRepository;
    @Mock
    IRestaurantRepository restaurantRepository;
    @Mock
    DiscountContext discountContext;
    @Mock
    OrderEventManager orderEventManager;
    @Mock
    IDeliveryAgentRepository deliveryAgentRepository;
    @Mock
    IMenuItemRepository menuItemRepository;
    @Mock
    IDeliveryService deliveryService;
    IOrderService orderService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository,restaurantRepository,discountContext,orderEventManager,menuItemRepository,deliveryAgentRepository,deliveryService);
    }
    @Test
    void testCreateOrder(){
        Customer customer = new Customer();
        Restaurant restaurant = new Restaurant();
        Order order = orderService.createOrder(customer, restaurant);
        assertNotNull(order);
    }

    @Test
    void testAddItemToOrder(){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        menuItems.add(item);
        restaurant.setMenu(menuItems);
        when(menuItemRepository.findByRestaurant(1)).thenReturn(menuItems);
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        Customer customer = new Customer();
        Order order = orderService.createOrder(customer, restaurant);
        orderService.addItemToOrder(order,1,2);
        assertNotNull(order.getItems());
        assertEquals(1, order.getItems().size());
        assertEquals(2, order.getItems().get(0).getQuantity());
    }

    @Test
    void testConfirmOrder(){
        Customer customer = new Customer();
        Restaurant restaurant = new Restaurant();
        Order order = orderService.createOrder(customer, restaurant);
        restaurant.setRestaurantId(1);
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        when(menuItemRepository.findByRestaurant(1)).thenReturn(menuItems);
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        menuItems.add(item);
        restaurant.setMenu(menuItems);
        orderService.addItemToOrder(order,1,2);
        when(menuItemRepository.findByRestaurant(1)).thenReturn(menuItems);
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        orderService.confirmOrder(order);
        verify(orderRepository).save(order);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void testCancelOrder(){
        Customer customer = new Customer();
        Restaurant restaurant = new Restaurant();
        Order order = orderService.createOrder(customer, restaurant);
        order.setOrderId(1);
        MenuItem item = new MenuItem(1,"item1",200.0,MenuCategory.MAIN,"descr");
        when(menuItemRepository.findById(1)).thenReturn(Optional.of(item));
        orderService.addItemToOrder(order,1,2);
        orderService.confirmOrder(order);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        orderService.cancelOrder(1);
        verify(orderRepository).update(order);
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertEquals(PaymentStatus.REFUNDED, order.getPaymentStatus());
    }
    @Test
    void testRestoreOrder() {
        Order order = new Order();
        order.setOrderId(1);
        orderService.restoreOrder(order);
        assertEquals(OrderStatus.PLACED, order.getStatus());
        verify(orderRepository).update(order);
    }

    @Test
    void testAdvanceOrderStatus_success() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.PLACED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        orderService.advanceOrderStatus(1);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        verify(orderRepository).update(order);
    }

    @Test
    void testAdvanceOrderStatus_finalState() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.DELIVERED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        assertThrows(InvalidOrderOperationException.class,
                () -> orderService.advanceOrderStatus(1));
    }

    @Test
    void testAdvanceOrderStatus_noAgentAvailable() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.PREPARING);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        when(deliveryService.getAvailableAgents(anyInt())).thenReturn(List.of());
        assertThrows(InvalidOrderOperationException.class,
                () -> orderService.advanceOrderStatus(1));
    }

    @Test
    void testMarkDelivered_success() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.OUT_FOR_DELIVERY);
        DeliveryAgent agent = new DeliveryAgent();
        order.assignAgent(agent);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        orderService.markDelivered(1);
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
        assertEquals(PaymentStatus.SUCCESS, order.getPaymentStatus());
        verify(orderRepository).update(order);
        verify(deliveryAgentRepository).update(agent);
    }

    @Test
    void testMarkDelivered_invalidState() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.PLACED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        assertThrows(InvalidOrderOperationException.class,
                () -> orderService.markDelivered(1));
    }

    @Test
    void testUpdateOrderStatus_success() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.PLACED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        orderService.updateOrderStatus(1, OrderStatus.CONFIRMED);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        verify(orderRepository).update(order);
    }

    @Test
    void testUpdateOrderStatus_backward() {
        Order order = new Order();
        order.setOrderId(1);
        order.updateStatus(OrderStatus.CONFIRMED);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        assertThrows(InvalidOrderOperationException.class,
                () -> orderService.updateOrderStatus(1, OrderStatus.PLACED));
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        Order result = orderService.getOrderById(1);
        assertEquals(order, result);
    }

    @Test
    void testGetOrdersByCustomer() {
        when(orderRepository.findByCustomerId(1)).thenReturn(List.of(new Order()));
        List<Order> orders = orderService.getOrdersByCustomer(1);
        assertFalse(orders.isEmpty());
    }

    @Test
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(), new Order()));
        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
    }

}
