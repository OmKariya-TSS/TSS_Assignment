package com.tss.FoodAppTest.service;

import com.tss.FoodApp.exceptions.ValidationException;
import com.tss.FoodApp.model.Customer;
import com.tss.FoodApp.model.User;
import com.tss.FoodApp.repository.interfaces.IUserRepository;
import com.tss.FoodApp.service.implementations.AuthServiceImpl;
import com.tss.FoodApp.service.interfaces.IAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    @Mock
    private IUserRepository userRepo;

    private IAuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(userRepo);
    }

    @Test
    void testLogin_success() {
        User user = new Customer();
        user.setEmail("test@example.com");
        user.setPassword("pass123");
        user.setName("John Doe");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User loggedIn = authService.login("test@example.com", "pass123");

        assertNotNull(loggedIn);
        assertEquals("John Doe", loggedIn.getName());
        assertTrue(authService.isLoggedIn());
        assertEquals(user, authService.getCurrentUser());
    }

    @Test
    void testLogin_emailNotFound() {
        when(userRepo.findByEmail("missing@example.com")).thenReturn(Optional.empty());
        ValidationException e = assertThrows(ValidationException.class,
                () -> authService.login("missing@example.com", "pass123"));
        assertEquals("User with email missing@example.com not found.", e.getMessage());
    }

    @Test
    void testLogin_wrongPassword() {
        User user = new Customer();
        user.setEmail("test@example.com");
        user.setPassword("pass123");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        ValidationException e = assertThrows(ValidationException.class,
                () -> authService.login("test@example.com", "wrongpass"));

        assertEquals("Incorrect password.", e.getMessage());
    }

    @Test
    void testRegister_success() {
        Customer customer = new Customer();
        customer.setEmail("new@example.com");
        customer.setName("Jane Doe");

        when(userRepo.findByEmail("new@example.com")).thenReturn(Optional.empty());

        boolean result = authService.register(customer);
        assertTrue(result);
        verify(userRepo, times(1)).save(customer);
    }

    @Test
    void testRegister_emailExists() {
        Customer existing = new Customer();
        existing.setEmail("existing@example.com");

        when(userRepo.findByEmail("existing@example.com")).thenReturn(Optional.of(existing));

        Customer newCustomer = new Customer();
        newCustomer.setEmail("existing@example.com");

        ValidationException ex = assertThrows(ValidationException.class,
                () -> authService.register(newCustomer));

        assertEquals("Email already registered.", ex.getMessage());
        verify(userRepo, never()).save(any());
    }

    @Test
    void testLogout() {
        User user = new Customer();
        user.setEmail("test@example.com");
        user.setPassword("pass123");

        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        authService.login("test@example.com", "pass123");
        authService.logout();

        assertFalse(authService.isLoggedIn());
        assertNull(authService.getCurrentUser());
    }
}
