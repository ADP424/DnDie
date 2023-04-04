package com.products.api.productsapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.products.api.productsapi.persistence.UserDAO;
import com.products.api.productsapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the User Controller class
 * 
 * @author Team 1
 */
@Tag("Controller-tier")
public class UserControllerTest {
    private UserController userController;
    private UserDAO mockUserDAO;

    /**
     * Before each test, create a new UserController object and inject
     * a mock User DAO
     */
    @BeforeEach
    public void setupUserController() {
        mockUserDAO = mock(UserDAO.class);
        userController = new UserController(mockUserDAO);
    }

    @Test
    public void testGetUser() throws IOException {
        // Declare a mock user
        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);
        // When the same id is passed in, our mock User DAO will return the User object
        when(mockUserDAO.getUser(user.getUsername())).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.getUser(user.getUsername());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testGetUserNotFound() throws Exception {
        // Setup
        String username = "lolIdon'texist";
        // When the same username is passed in, our mock User DAO will return null, simulating
        // no user found
        when(mockUserDAO.getUser(username)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = userController.getUser(username);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetUserHandleException() throws Exception {
        // Setup
        String username = "lolIdon'texist";
        // When getUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUser(username);

        // Invoke
        ResponseEntity<User> response = userController.getUser(username);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws IOException {
        // Setup
        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);
        // when createUser is called, return true simulating successful
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(user);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(user,response.getBody());
    }

    @Test
    public void testCreateUserFailed() throws IOException {
        // Setup
        User user = new User("Mdbook", "s3cure", "Mikayla", "Burke", 2, true);
        // when createUser is called, return false simulating failed
        // creation and save
        when(mockUserDAO.createUser(user)).thenReturn(null);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateUserHandleException() throws IOException {
        // Setup
        User user = new User("JohnSmith", "password", "John", "Smith", 14, false);

        // When createUser is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).createUser(user);

        // Invoke
        ResponseEntity<User> response = userController.createUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetUsers() throws IOException {
        // Setup
        User[] users = new User[2];
        users[0] = new User("BeanLord", "beans", "Kai", "Louie", 42069, false);
        users[1] = new User("Milk", "beanerrors", "Alex", "Beekman", 69420, false);

        // When getUsers is called return the users created above
        when(mockUserDAO.getUsers()).thenReturn(users);

        // Invoke
        ResponseEntity<User[]> response = userController.getUsers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testGetUsersFailed() throws IOException {
        // Setup
        User[] users = new User[2];
        users[0] = new User("BeanLord", "beans", "Kai", "Louie", 42069, false);
        users[1] = new User("Milk", "beanerrors", "Alex", "Beekman", 69420, false);

        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockUserDAO.getUsers()).thenReturn(null);

        // Invoke
        ResponseEntity<User[]> response = userController.getUsers();

        // Analyze
        assertEquals(response.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void testGetUsersHandleException() throws IOException {
        // Setup
        // When getUsers is called on the Mock User DAO, throw an IOException
        doThrow(new IOException()).when(mockUserDAO).getUsers();

        // Invoke
        ResponseEntity<User[]> response = userController.getUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
