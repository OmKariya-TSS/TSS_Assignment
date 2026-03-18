package com.tss.FoodAppTest.service;


import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.DeliveryAgent;
import com.tss.FoodApp.model.Restaurant;
import com.tss.FoodApp.repository.interfaces.IRestaurantRepository;
import com.tss.FoodApp.service.implementations.AgentAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgentAuthServiceTest {

    @Mock
    private IRestaurantRepository restaurantRepo;
    private AgentAuthServiceImpl authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        authService = new AgentAuthServiceImpl(restaurantRepo);
    }

    @Test
    void testLogin_success() {
        DeliveryAgent agent = new DeliveryAgent(1, "John Doe", "9876543210", "pass123");
        Restaurant rest = new Restaurant();
        rest.setRestaurantId(1);
        rest.setName("Test Resto");
        rest.setAgents(List.of(agent));
        when(restaurantRepo.findAll()).thenReturn(List.of(rest));
        DeliveryAgent loggedIn = authService.login("9876543210", "pass123");
        assertNotNull(loggedIn);
        assertEquals("John Doe", loggedIn.getName());
        assertTrue(authService.isLoggedIn());
        assertEquals(agent, authService.getCurrentAgent());
    }

    @Test
    void testLogin_invalidCredentials() {
        when(restaurantRepo.findAll()).thenReturn(List.of());
        ValidationException ex = assertThrows(ValidationException.class,
                () -> authService.login("9876543210", "wrongpass"));
        assertEquals("Invalid credentials", ex.getMessage());
        assertFalse(authService.isLoggedIn());
    }

    @Test
    void testLogin_emptyPhone() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> authService.login("", "pass123"));
        assertEquals("Phone cannot be empty", ex.getMessage());
    }

    @Test
    void testLogin_emptyPassword() {
        ValidationException ex = assertThrows(ValidationException.class,
                () -> authService.login("9876543210", ""));
        assertEquals("Password cannot be empty", ex.getMessage());
    }

    @Test
    void testLogout() {
        DeliveryAgent agent = new DeliveryAgent(1, "John Doe", "9876543210", "pass123");
        Restaurant rest = new Restaurant();
        rest.setRestaurantId(1);
        rest.setName("Test Resto");
        rest.setAgents(List.of(agent));
        when(restaurantRepo.findAll()).thenReturn(List.of(rest));
        agent = authService.login("9876543210", "pass123");
        authService.logout();
        assertFalse(authService.isLoggedIn());
        assertNull(authService.getCurrentAgent());
    }
    @Test
    void testLogout_whenNotLoggedIn() {
        authService.logout();
        assertFalse(authService.isLoggedIn());
        assertNull(authService.getCurrentAgent());
    }

    @Test
    void testGetCurrentAgent() {
        DeliveryAgent agent = new DeliveryAgent(1, "Agent1", "9999999999", "password");
        Restaurant restaurant = new Restaurant();
        restaurant.setAgents(List.of(agent));
        when(restaurantRepo.findAll()).thenReturn(List.of(restaurant));
        authService.login("9999999999", "password");
        assertEquals(agent, authService.getCurrentAgent());
    }
}