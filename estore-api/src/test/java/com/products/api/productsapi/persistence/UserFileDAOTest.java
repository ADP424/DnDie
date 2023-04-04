package com.products.api.productsapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.productsapi.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the User File DAO class
 * 
 * @author Team 1
 */
@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    User[] testUsers;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupUserFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new User[3];
        testUsers[0] = new User("BeanLord", "beans", "Kai", "Louie", 42069, false);
        testUsers[1] = new User("JohnSmith", "password", "John", "Smith", 14, false);
        testUsers[2] = new User("Mdbook", "s3cure", "Mikayla", "Burke", 2, true);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the user array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),User[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetUsers() {
        // Invoke
        User[] users = userFileDAO.getUsers();

        for(int i = 0; i < testUsers.length; i++) {
            System.out.println(testUsers[i].getUsername() + " / " + users[i].getUsername());
        }

        // Analyze
        assertEquals(users.length, testUsers.length);
        for (int i = 0; i < testUsers.length; ++i)
            assertEquals(users[i], testUsers[i]);
    }

    @Test
    public void testGetUser() {
        // Invoke
        User user = userFileDAO.getUser("JohnSmith");

        // Analzye
        assertEquals(user, testUsers[1]);
    }

    @Test
    public void testCreateUser() {
        // Setup
        User user = new User("Milk", "beanerrors", "Alex", "Beekman", 42070, false);

        // Invoke
        User result = assertDoesNotThrow(() -> userFileDAO.createUser(user),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        User actual = userFileDAO.getUser(user.getUsername());
        assertEquals(actual.getUsername(), user.getUsername());
        assertEquals(actual.getPassword(), user.getPassword());
        assertEquals(actual.getFirstName(), user.getFirstName());
        assertEquals(actual.getLastName(), user.getLastName());
        assertEquals(actual.getCartNumber(), user.getCartNumber());
        assertEquals(actual.get_is_admin(), user.get_is_admin());
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(User[].class));

        User user = new User("Aodhan", "12345", "Aidan", "Dalgarno-Platt", 1, false);;

        assertThrows(IOException.class,
                        () -> userFileDAO.createUser(user),
                        "IOException not thrown");
    }

    @Test
    public void testGetUserNotFound() {
        // Invoke
        User user = userFileDAO.getUser("lmao I don't exist");

        // Analyze
        assertEquals(user,null);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the UserFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),User[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new UserFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
