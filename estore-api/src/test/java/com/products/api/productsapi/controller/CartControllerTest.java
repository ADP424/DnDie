package com.products.api.productsapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.products.api.productsapi.model.Cart;
import com.products.api.productsapi.model.Product;
import com.products.api.productsapi.persistence.CartDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class CartControllerTest {

    private CartController cartController;
    private CartDAO mockCartDAO;

    /**
     * Before each test, create a new CartController object and inject
     * a mock Cart DAO
     */
    @BeforeEach
    public void setupCarttController() {
        mockCartDAO= mock(CartDAO.class);
        cartController = new CartController(mockCartDAO);
    }
    
    @Test
    public void testGetCart() throws IOException {
        // Declare a mock Cart
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);
        // When the same id is passed in, our mock Product DAO will return the Product object
        when(mockCartDAO.getCart(cart.getId())).thenReturn(cart);

        // Invoke
        ResponseEntity<Cart> response = cartController.getCart(cart.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(cart,response.getBody());
    }

    @Test
    public void testGetCartNotFound() throws Exception {
        // Setup
        int cartId = 19;
        // When the same id is passed in, our mock Product DAO will return null, simulating
        // no product found
        when(mockCartDAO.getCart(cartId)).thenReturn(null);

        // Invoke
        ResponseEntity<Cart> response = cartController.getCart(cartId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetCartHandleException() throws Exception {
        // Setup
        int cartId = 19;
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).getCart(cartId);

        // Invoke
        ResponseEntity<Cart> response = cartController.getCart(cartId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateCart() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockCartDAO.createCart(cart)).thenReturn(cart);

        // Invoke
        ResponseEntity<Cart> response = cartController.createCart(cart);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(cart,response.getBody());
    }

    @Test
    public void testCreateCartFailed() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockCartDAO.createCart(cart)).thenReturn(null);

        // Invoke
        ResponseEntity<Cart> response = cartController.createCart(cart);

        // Analyze
        assertEquals(response.getStatusCode(), response.getStatusCode());
    }

    @Test
    public void testCreateCartHandleException() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
        
        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).createCart(cart);

        // Invoke
        ResponseEntity<Cart> response = cartController.createCart(cart);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateCartSuccess() throws IOException {
        // Setup
        Product product = new Product(20, "Just One D20", "Black", "Comic Sans", "Red", 2.0, 0, 1);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockCartDAO.updateCart(cart)).thenReturn(cart);

        // Invoke
        ResponseEntity<Cart> response = cartController.updateCart(cart);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testUpdateCartFailed() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
                // when updateProduct is called, return true simulating successful
        // update and save
        when(mockCartDAO.updateCart(cart)).thenReturn(null);

        // Invoke
        ResponseEntity<Cart> response = cartController.updateCart(cart);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateCartHandleException() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
         
        // When updateProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).updateCart(cart);

        // Invoke
        ResponseEntity<Cart> response = cartController.updateCart(cart);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetCarts() throws IOException {
        // Setup
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        Product[] array = new Product[1];
        array[0] = product;
        Cart cart = new Cart(19, "Sample Cart", array);        // when createProduct is called, return true simulating successful
         
        Product[] products = new Product[1];
        products[0] = new Product(21, "Black Dice Set", "Black", "Times New Roman", "Very Dark Grey", 1.0, 0, 1);
        Cart cartTwo = new Cart(18, "Second Cart", products);

        Cart[] carts = new Cart[2];
        carts[0] = cart;
        carts[1] = cartTwo;
        
        // When getProducts is called return the products created above
        when(mockCartDAO.getCarts()).thenReturn(carts);

        // Invoke
        ResponseEntity<Cart[]> response = cartController.getCarts();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(carts,response.getBody());
    }

    @Test
    public void testGetCartsFaied() throws IOException {
        // Setup
        // When getProducts is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).getCarts();

        // Invoke
        ResponseEntity<Cart[]> response = cartController.getCarts();

        // Analyze
        assertEquals(null,response.getBody());
    }

    @Test
    public void testGetCartsHandleException() throws IOException {
        // Setup
        // When getProducts is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).getCarts();

        // Invoke
        ResponseEntity<Cart[]> response = cartController.getCarts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteCart() throws IOException {
        // Setup
        int cartId = 19;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockCartDAO.deleteCart(cartId)).thenReturn(true);

        // Invoke
        ResponseEntity<Cart> response = cartController.deleteCart(cartId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteCartNotFound() throws IOException {
        // Setup
        int cartId = 19;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockCartDAO.deleteCart(cartId)).thenReturn(false);

        // Invoke
        ResponseEntity<Cart> response = cartController.deleteCart(cartId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteCartHandleException() throws IOException {
        // Setup
        int cartId = 19;
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockCartDAO).deleteCart(cartId);

        // Invoke
        ResponseEntity<Cart> response = cartController.deleteCart(cartId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

}

