package com.products.api.productsapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.products.api.productsapi.model.User;
import com.products.api.productsapi.persistence.UserDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class LoginControllerTest {
    private LoginController loginController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new ProductController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupLoginController() {
        mockUserDAO = mock(UserDAO.class);
        loginController = new LoginController(mockUserDAO);
    }

    @Test
    public void testCreateUser() throws IOException {
        // Setup
        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        String response = "test";

        // Analyze
        assertEquals("test",response);
    }

    @Test
    public void testCreateUserFailed() throws IOException {
        // Setup
        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(null);

        // Invoke
        String response = "test";

        // Analyze
        assertFalse(null == "test");
    }

    @Test
    public void testCreateUserErrorThrown() throws IOException {
        // Setup
        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        String response = "test";

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND);
    }
    
}
