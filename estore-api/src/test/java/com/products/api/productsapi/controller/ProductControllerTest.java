package com.products.api.productsapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.products.api.productsapi.persistence.ProductDAO;
import com.products.api.productsapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Product Controller class
 * 
 * @author Team 1
 */
@Tag("Controller-tier")
public class ProductControllerTest {
    private ProductController productController;
    private ProductDAO mockProductDAO;

    /**
     * Before each test, create a new ProductController object and inject
     * a mock Product DAO
     */
    @BeforeEach
    public void setupProductController() {
        mockProductDAO = mock(ProductDAO.class);
        productController = new ProductController(mockProductDAO);
    }

    @Test
    public void testGetProduct() throws IOException {
        // Declare a mock product
        Product product = new Product(20, "Deluxe Red Dice Set", "Red", "Arial", "Black", 2.0, 0, 4);
        // When the same id is passed in, our mock Product DAO will return the Product object
        when(mockProductDAO.getProduct(product.getId())).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.getProduct(product.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        // Setup
        int productId = 20;
        // When the same id is passed in, our mock Product DAO will return null, simulating
        // no product found
        when(mockProductDAO.getProduct(productId)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = productController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetProductHandleException() throws Exception {
        // Setup
        int productId = 20;
        // When getProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).getProduct(productId);

        // Invoke
        ResponseEntity<Product> response = productController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testCreateProduct() throws IOException {
        // Setup
        Product product = new Product(20, "Wi-Fire Dice Set", "Light Blue", "Wing Dings", "Gold", 3.99, 0, 3);
        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockProductDAO.createProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(product,response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException {
        // Setup
        Product product = new Product(20, "Zap", "Yellow", "Bolt Regular", "Dark Blue", 2.99, 0, 1);
        // when createProduct is called, return false simulating failed
        // creation and save
        when(mockProductDAO.createProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = productController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateProductHandleException() throws IOException {
        // Setup
        Product product = new Product(20, "Smol Dice Set", "Small", "Very Small", "So Tiny You Can't Even See the Color", 2.5, 0, 1);

        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).createProduct(product);

        // Invoke
        ResponseEntity<Product> response = productController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testUpdateProductSuccess() throws IOException {
        // Setup
        Product product = new Product(20, "Just One D20", "Black", "Comic Sans", "Red", 2.0, 0, 1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockProductDAO.updateProduct(product)).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = productController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testUpdateProductFailed() throws IOException {
        // Setup
        Product product = new Product(20, "Just One D20", "Black", "Comic Sans", "Red", 2.0, 0, 1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockProductDAO.updateProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = productController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateProductHandleException() throws IOException {
        // Setup
        Product product = new Product(20, "Lobter Special Dice Set", "Orange-Red", "Friz-Quadrata", "Black", 1.0, 0, 1);
        // When updateProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).updateProduct(product);

        // Invoke
        ResponseEntity<Product> response = productController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetProducts() throws IOException {
        // Setup
        Product[] products = new Product[2];
        products[0] = new Product(20, "Black Dice Set", "Black", "Times New Roman", "Very Dark Grey", 1.0, 0, 1);
        products[1] = new Product(21, "White Dice Set", "White", "Times New Roman", "Very Light Grey", 3.5, 0, 1);

        // When getProducts is called return the products created above
        when(mockProductDAO.getProducts()).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = productController.getProducts();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());
    }

    @Test
    public void testGetProductsFailed() throws IOException {
        // Setup
        Product[] products = new Product[2];
        products[0] = new Product(20, "Black Dice Set", "Black", "Times New Roman", "Very Dark Grey", 1.0, 0, 1);
        products[1] = new Product(21, "White Dice Set", "White", "Times New Roman", "Very Light Grey", 3.5, 0, 1);

        // When getProducts is called return the products created above
        when(mockProductDAO.getProducts()).thenReturn(null);

        // Invoke
        ResponseEntity<Product[]> response = productController.getProducts();

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertTrue(null == response.getBody());
    }

    @Test
    public void testGetProductsHandleException() throws IOException {
        // Setup
        // When getProducts is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).getProducts();

        // Invoke
        ResponseEntity<Product[]> response = productController.getProducts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchProducts() throws IOException {
        // Setup
        String searchString = "Ol' Dice Set";
        Product[] products = new Product[2];
        products[0] = new Product(20, "Big Ol' Dice Set", "Dark Green", "Arial", "Pink", 2.0, 0, 1);
        products[1] = new Product(21, "Medium Ol' Dice Set", "Vomit Green", "Times New Roman", "White", 5.0, 0, 1);

        // When findProducts is called with the search string, return the two
        /// products above
        when(mockProductDAO.findProducts(searchString)).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());
    }

    @Test
    public void testSearchProductsFailed() throws IOException {
        // Setup
        String searchString = "Ol' Dice Set";
        Product[] products = new Product[2];
        products[0] = new Product(20, "Big Ol' Dice Set", "Dark Green", "Arial", "Pink", 2.0, 0, 1);
        products[1] = new Product(21, "Medium Ol' Dice Set", "Vomit Green", "Times New Roman", "White", 5.0, 0, 1);

        // When findProducts is called with the search string, return the two
        /// products above
        when(mockProductDAO.findProducts(searchString)).thenReturn(null);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(null,response.getBody());
    }

    @Test
    public void testSearchProductsHandleException() throws IOException {
        // Setup
        String searchString = "di";
        // When createProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).findProducts(searchString);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteProduct() throws IOException {
        // Setup
        int productId = 20;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockProductDAO.deleteProduct(productId)).thenReturn(true);

        // Invoke
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteProductNotFound() throws IOException {
        // Setup
        int productId = 20;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockProductDAO.deleteProduct(productId)).thenReturn(false);

        // Invoke
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteProductHandleException() throws IOException {
        // Setup
        int productId = 20;
        // When deleteProduct is called on the Mock Product DAO, throw an IOException
        doThrow(new IOException()).when(mockProductDAO).deleteProduct(productId);

        // Invoke
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
