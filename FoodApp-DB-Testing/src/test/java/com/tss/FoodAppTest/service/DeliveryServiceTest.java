package com.tss.FoodAppTest.service;

import com.tss.FoodApp.enums.OrderStatus;
import com.tss.FoodApp.exceptions.AgentNotAvailableException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Order;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IDeliveryAgentRepository;
import com.tss.FoodApp.repository.interfaces.IOrderRepository;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.implementations.DeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeliveryServiceTest {
    @Mock
    private IRestaurantRepository restaurantRepo;
    @Mock
    private IDeliveryAgentRepository agentRepo;
    @Mock
    private IOrderRepository orderRepo;

    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        deliveryService = new DeliveryServiceImpl(restaurantRepo, agentRepo, orderRepo);
    }
    @Test
    void testAssignAgent_success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("TestResto");

        DeliveryAgent agent = new DeliveryAgent(1, "John", "1234567890", "pass");
        agent.setAvailable(true);

        Order order = new Order();
        order.setOrderId(100);
        order.setRestaurant(restaurant);

        when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
        when(agentRepo.findByRestaurant(1)).thenReturn(List.of(agent));

        DeliveryAgent assigned = deliveryService.assignAgent(order);

        assertEquals(agent, assigned);
        assertFalse(assigned.isAvailable());
        assertEquals(agent, order.getAssignedAgent());

        verify(agentRepo).update(agent);
        verify(orderRepo).update(order);
    }

    @Test
    void testAssignAgent_noAvailableAgents() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("TestResto");

        Order order = new Order();
        order.setOrderId(101);
        order.setRestaurant(restaurant);

        when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
        when(agentRepo.findByRestaurant(1)).thenReturn(List.of());

        assertThrows(AgentNotAvailableException.class,
                () -> deliveryService.assignAgent(order));
    }

    @Test
    void testMarkDelivered_success() {
        DeliveryAgent agent = new DeliveryAgent(1, "John", "1234567890", "password");
        agent.setAvailable(false);

        Order order = new Order();
        order.setOrderId(200);
        order.assignAgent(agent);

        deliveryService.markDelivered(order);

        assertTrue(agent.isAvailable());
        assertEquals(1, agent.getTotalDeliveries());
        assertEquals(OrderStatus.DELIVERED, order.getStatus());

        verify(agentRepo).update(agent);
        verify(orderRepo).update(order);
    }

    @Test
    void testAddAgent_success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(1);

        DeliveryAgent agent = new DeliveryAgent(0, "Alice", "9876543210", "password");

        when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));

        deliveryService.addAgent(1, agent);

        assertTrue(agent.isAvailable());
        verify(agentRepo).save(agent, 1);
    }

    @Test
    void testRemoveAgent_success() {
        deliveryService.removeAgent(5);
        verify(agentRepo).delete(5);
    }
    @Test
    void testGetAvailableAgents() {
        DeliveryAgent a1 = new DeliveryAgent(1, "John", "1111111111", "password");
        DeliveryAgent a2 = new DeliveryAgent(2, "Jane", "2222222222", "password");
        a1.setAvailable(true);
        a2.setAvailable(false);

        when(agentRepo.findByRestaurant(1)).thenReturn(List.of(a1, a2));

        List<DeliveryAgent> available = deliveryService.getAvailableAgents(1);

        assertEquals(1, available.size());
        assertEquals(a1, available.get(0));
    }

}
