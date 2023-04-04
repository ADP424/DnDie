package com.products.api.productsapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.productsapi.model.Cart;
import com.products.api.productsapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Persistence-tier")
public class CartFileDAOTest {
    CartFileDAO cartFileDAO;
    Cart[] testCarts;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);

        Product[] firstProduct = new Product[1];
        firstProduct[0] = new Product(99,"Wi-Fire Dice Set", "Blue", "Comic Sans", "Red", 6.95, 0, 2);
        Product[] secondProduct = new Product[1];
        secondProduct[0] = new Product(100,"Deluxe Red Dice Set", "Red", "Ariel", "Black", 2.99, 0, 1);
        Product[] thirProducts = new Product[1];
        thirProducts[0] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);

        testCarts = new Cart[3];
        testCarts[0] = new Cart(99, "Cart One", firstProduct);
        testCarts[1] = new Cart(100, "Cart Two", secondProduct);
        testCarts[2] = new Cart(101, "Cart Three", thirProducts);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the product array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Cart[].class))
                .thenReturn(testCarts);
        cartFileDAO = new CartFileDAO("doesnt_matter.txt", mockObjectMapper);
    }
    
    @Test
    public void testGetProducts() {
        // Invoke
        Cart[] carts = cartFileDAO.getCarts();

        // Analyze
        assertEquals(carts.length, testCarts.length);
        for (int i = 0; i < testCarts.length;++i)
            assertEquals(carts[i], testCarts[i]);
    }

    @Test
    public void testGetCart() {
        // Invoke
        Cart product = cartFileDAO.getCart(99);

        // Analzye
        assertEquals(product, testCarts[0]);
    }

    @Test
    public void testDeleteCart() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> cartFileDAO.deleteCart(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test carts array - 1 (because of the delete)
        assertEquals(cartFileDAO.carts.size(),testCarts.length-1);
    }

    @Test
    public void testCreateCart() {
        // Setup
        Product[] firstProduct = new Product[1];
        firstProduct[0] = new Product(99,"Wi-Fire Dice Set", "Blue", "Comic Sans", "Red", 6.95, 0, 2);

        Cart cart = new Cart(99, "Cart One", firstProduct);

        // Invoke
        Cart result = assertDoesNotThrow(() -> cartFileDAO.createCart(cart),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Cart actual = cartFileDAO.getCart(cart.getId());
        assertEquals(actual.getId(),cart.getId());
        assertEquals(actual.getName(),cart.getName());
        assertEquals(actual.getCartProducts()[0].getId(),cart.getCartProducts()[0].getId());
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Cart[].class));

        Product[] firstProduct = new Product[1];
        firstProduct[0] = new Product(99,"Wi-Fire Dice Set", "Blue", "Comic Sans", "Red", 6.95, 0, 2);
        
        Cart cart = new Cart(99, "Cart One", firstProduct);

        assertThrows(IOException.class,
                        () -> cartFileDAO.createCart(cart),
                        "IOException not thrown");
    }

    @Test
    public void testGetProductNotFound() {
        // Invoke
        Cart product = cartFileDAO.getCart(696969);

        // Analyze
        assertEquals(product,null);
    }

    @Test
    public void testDeleteProductNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> cartFileDAO.deleteCart(420420420),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(cartFileDAO.carts.size(),testCarts.length);
    }

    @Test
    public void testUpdateCart() throws IOException {
        Product[] thirProducts = new Product[1];
        thirProducts[0] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);

        Cart cart = new Cart(99, "Cart maybe", thirProducts);
        
        // Invoke
        cartFileDAO.updateCart(cart);
        String name = "Cart maybe";        

        // Analzye
        assertEquals(name, cart.getName());
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("uwu.txt"),Cart[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new CartFileDAO("uwu.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
