package com.products.api.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class CartTest {
    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "Sample Cart";
        Product[] expected_array = new Product[1];

        // Invoke
        Cart cart = new Cart(expected_id, expected_name, expected_array);

        // Analyze
        assertEquals(expected_id, cart.getId());
        assertEquals(expected_name,cart.getName());
        assertEquals(expected_array,cart.getCartProducts());
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];

        Cart cart = new Cart(id, name, array);

        String expected_name = "Sample Cart";

        // Analyze
        assertEquals(expected_name, cart.getName());
    }

    public void testNameNotFound() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];

        Cart cart = new Cart(id, name, array);

        String expected_name = "Sample Cart No";

        // Analyze
        assertFalse(expected_name == cart.getName());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];
        String expected_string = String.format(Cart.STRING_FORMAT, id, name);


        Cart cart = new Cart(id, name, array);

        // Invoke
        String actual_string = cart.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testToStringNo() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];
        String expected_string = String.format(Cart.STRING_FORMAT, id, name);


        Cart cart = new Cart(id, name, array);

        // Invoke
        String actual_string = cart.toString();
        actual_string += "no";

        // Analyze
        assertFalse(expected_string == actual_string);
    }

    @Test
    public void testId() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];

        Cart cart = new Cart(id, name, array);

        int expected_id = 99;

        // Analyze
        assertEquals(expected_id, cart.getId());
    }

    @Test
    public void testIdNo() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];

        Cart cart = new Cart(id, name, array);

        int expected_id = 100;

        // Analyze
        assertFalse(expected_id == cart.getId());
    }

    @Test
    public void testCartProducts() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];
        array[0] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);

        Cart cart = new Cart(id, name, array);

        Product[] expected_cart = new Product[1];
        expected_cart[0] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);


        // Analyze
        assertEquals(expected_cart[0].getName(), cart.getCartProducts()[0].getName());
    }

    @Test
    public void testCartProductsNo() {
        // Setup
        int id = 99;
        String name = "Sample Cart";
        Product[] array = new Product[1];
        array[0] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);

        Cart cart = new Cart(id, name, array);

        Product[] expected_cart = new Product[1];
        expected_cart[0] = new Product(101,"Crab Dice Set No", "Dark Orange", "Lobster", "White", 2.4, 0, 4);


        // Analyze
        assertFalse(expected_cart[0].getName() == cart.getCartProducts()[0].getName());
    }

    @Test
    public void testSetName() {
        // Setup
        int id = 99;
        String name = "Sample Cart 2";
        Product[] array = new Product[1];

        Cart cart = new Cart(id, name, array);

        String expected_name = "Sample Cart";
        cart.setName("Sample Cart");

        // Analyze
        assertEquals(expected_name, cart.getName());
    }
}