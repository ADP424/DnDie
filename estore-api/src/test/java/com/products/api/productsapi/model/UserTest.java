package com.products.api.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the User class
 * 
 * @author Team 1
 */
@Tag("Model-tier")
public class UserTest {
    @Test
    public void testCtor() {
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        // Invoke
        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Analyze
        assertEquals(expected_username, user.getUsername());
        assertEquals(expected_password,user.getPassword());
        assertEquals(expected_firstname,user.getFirstName());
        assertEquals(expected_lastname,user.getLastName());
        assertEquals(expected_cart_number,user.getCartNumber());
        assertEquals(expected_admin_privileges,user.get_is_admin());
    }

    @Test
    public void testUsername() {
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        String expected_name = "JohnSmith";

        // Analyze
        assertEquals(expected_name,user.getUsername());
    }

    @Test
    public void testToString() {
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        String expected_string = String.format(User.STRING_FORMAT, expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);
        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        String actual_string = user.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testPassword(){
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        String actual_password = user.getPassword();

        // Analyze
        assertEquals(expected_password, actual_password);
    }

    @Test
    public void testFirstname(){
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        String actual_firstname = user.getFirstName();

        // Analyze
        assertEquals(expected_firstname, actual_firstname);
    }

    @Test
    public void testLastname(){
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        String actual_lastname = user.getLastName();

        // Analyze
        assertEquals(expected_lastname, actual_lastname);
    }

    @Test
    public void testCartNumber(){
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        int actual_cart_number = user.getCartNumber();

        // Analyze
        assertEquals(expected_cart_number, actual_cart_number);
    }

    @Test
    public void testIsAdmin(){
        // Setup
        String expected_username = "JohnSmith";
        String expected_password = "password";
        String expected_firstname = "Ines";
        String expected_lastname = "Villegas Costa";
        int expected_cart_number = 1;
        boolean expected_admin_privileges = false;

        User user = new User(expected_username, expected_password, expected_firstname, expected_lastname, expected_cart_number, expected_admin_privileges);

        // Invoke
        boolean actual_admin_privileges = user.get_is_admin();

        // Analyze
        assertEquals(expected_admin_privileges, actual_admin_privileges);
    }
}