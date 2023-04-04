package com.products.api.productsapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.api.productsapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Product File DAO class
 * 
 * @author Team 1
 */
@Tag("Persistence-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;
    Product[] testProducts;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupProductFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);

        testProducts = new Product[3];
        testProducts[0] = new Product(99,"Wi-Fire Dice Set", "Blue", "Comic Sans", "Red", 6.95, 0, 2);
        testProducts[1] = new Product(100,"Deluxe Red Dice Set", "Red", "Ariel", "Black", 2.99, 0, 1);
        testProducts[2] = new Product(101,"Crab Dice Set", "Dark Orange", "Lobster", "White", 2.4, 0, 4);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the product array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Product[].class))
                .thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("doesnt_matter.txt", mockObjectMapper);
    }

    @Test
    public void testGetProducts() {
        // Invoke
        Product[] products = productFileDAO.getProducts();

        // Analyze
        assertEquals(products.length, testProducts.length);
        for (int i = 0; i < testProducts.length;++i)
            assertEquals(products[i], testProducts[i]);
    }

    @Test
    public void testUniqueIds(){
        Product[] products = productFileDAO.getProducts();

        // Analyze
        for (int i = 0; i < products.length-1; ++i){
            assertTrue(products[i] != products[i+1]);
        }
    }

    @Test
    public void testFindProducts() {
        // Invoke (should only find the second product, which has "la" in "crab dice set")
        Product[] products = productFileDAO.findProducts("ra");

        // Analyze
        assertEquals(products.length, 1);
        assertEquals(products[0], testProducts[2]);
    }

    @Test
    public void testGetProduct() {
        // Invoke
        Product product = productFileDAO.getProduct(99);

        // Analzye
        assertEquals(product,testProducts[0]);
    }

    @Test
    public void testUpdateProduct() throws IOException {
        Product product = new Product(99,"Ocean Man Dice Set", "Deep Navy", "Pacifico", "Mossy Green", 2.4, 0, 1);

        // Invoke
        productFileDAO.updateProduct(product);
        String name = "Ocean Man Dice Set";        

        // Analzye
        assertEquals(name, product.getName());
    }

    @Test
    public void testUpdateProductFailed() throws IOException {
        Product product = new Product(98,"Ocean Man Dice Set", "Deep Navy", "Pacifico", "Mossy Green", 2.4, 0, 1);

        // Invoke
        productFileDAO.updateProduct(product);
        String name = "Ocean Man Dice Set";        

        // Analzye
        assertTrue(name == product.getName());
    }

    @Test
    public void testDeleteProduct() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test products array - 1 (because of the delete)
        // Because products attribute of ProductFileDAO is package private
        // we can access it directly
        assertEquals(productFileDAO.products.size(),testProducts.length-1);
    }

    @Test
    public void testCreateProduct() {
        // Setup
        Product product = new Product(102,"Ocean Man Dice Set", "Deep Navy", "Pacifico", "Mossy Green", 2.4, 0, 1);

        // Invoke
        Product result = assertDoesNotThrow(() -> productFileDAO.createProduct(product),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getId());
        assertEquals(actual.getId(),product.getId());
        assertEquals(actual.getName(),product.getName());
        assertEquals(actual.getDiceColor(),product.getDiceColor());
        assertEquals(actual.getFontType(),product.getFontType());
        assertEquals(actual.getFontColor(),product.getFontColor());
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Product[].class));

        Product product = new Product(102,"Wi-Fire Dice Set", "Blue", "Comic Sans", "Red", 2.4, 0, 1);

        assertThrows(IOException.class,
                        () -> productFileDAO.createProduct(product),
                        "IOException not thrown");
    }

    @Test
    public void testGetProductNotFound() {
        // Invoke
        Product product = productFileDAO.getProduct(98);

        // Analyze
        assertEquals(product,null);
    }

    @Test
    public void testDeleteProductNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(productFileDAO.products.size(),testProducts.length);
    }

    @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the ProductFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Product[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new ProductFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
