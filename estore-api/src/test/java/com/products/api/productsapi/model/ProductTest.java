package com.products.api.productsapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 * 
 * @author Team 1
 */
@Tag("Model-tier")
public class ProductTest {

    @Test
    public void testCtor() {
        // Setup
        int expected_id = 99;
        String expected_name = "Wi-Fire Dice Set";
        String expected_color = "Blue";
        String expected_font = "Comic Sans";
        String expected_font_color = "Red";
        double expected_price = 0.99;
        int expected_coupon = 0;
        int expected_quantity = 3;

        // Invoke
        Product product = new Product(expected_id,expected_name, expected_color, expected_font, expected_font_color, expected_price, expected_coupon, expected_quantity);

        // Analyze
        assertEquals(expected_id,product.getId());
        assertEquals(expected_name,product.getName());
        assertEquals(expected_color,product.getDiceColor());
        assertEquals(expected_font,product.getFontType());
        assertEquals(expected_font_color,product.getFontColor());
        assertEquals(expected_price,product.getPrice());
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 199.99;
        Product product = new Product(id, name, color, font, font_color, price, 0, 4);

        String expected_name = "Wi-Fire Dice Set";

        // Analyze
        assertEquals(expected_name,product.getName());
    }

    public void testNameNo() {
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 199.99;
        Product product = new Product(id, name, color, font, font_color, price, 0, 4);

        String expected_name = "Wi-Fire Dice Set 2";

        // Analyze
        assertFalse(expected_name == product.getName());
    }

    @Test
    public void testToString() {
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 19.59;
        int coupon = 1;
        int quantity = 2;
        String expected_string = String.format(Product.STRING_FORMAT, id, name, color, font, font_color, price, coupon, quantity);
        Product product = new Product(id, name, color, font, font_color, price, coupon, quantity);

        // Invoke
        String actual_string = product.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }

    @Test
    public void testToStringNo() {
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 19.59;
        int coupon = 1;
        int quantity = 2;
        String expected_string = String.format(Product.STRING_FORMAT, id, name, color, font, font_color, price, coupon, quantity);
        Product product = new Product(id, name, color, font, font_color, price, coupon, quantity);

        // Invoke
        String actual_string = product.toString();
        actual_string += "no";

        // Analyze
        assertFalse(expected_string == actual_string);
    }

    @Test
    public void testGetDiceColor(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 39.99;
        Product product = new Product(id, name, color, font, font_color, price, 0, 38);

        // Invoke
        String actual_color = product.getDiceColor();

        // Analyze
        assertEquals(color,actual_color);
    }

    @Test
    public void testGetDiceColorNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Yellow";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 39.99;
        Product product = new Product(id, name, color, font, font_color, price, 0, 38);

        // Invoke
        String actual_color = product.getDiceColor();

        // Analyze
        assertFalse("red" == actual_color);
    }

    @Test
    public void testGetFontType(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        String actual_font = product.getFontType();

        // Analyze
        assertEquals(font, actual_font);
    }

    @Test
    public void testGetFontTypeNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic no";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        String actual_font = product.getFontType();

        // Analyze
        assertFalse("black" == actual_font);
    }

    @Test
    public void testId(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        int actual_id = product.getId();

        // Analyze
        assertEquals(id, actual_id);
    }

    @Test
    public void testIdNo(){
        // Setup
        int id = 19;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        int actual_id = product.getId();

        // Analyze
        assertFalse(1 == actual_id);
    }

    @Test
    public void testFontColor(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        String actual_font = product.getFontColor();

        // Analyze
        assertEquals(font_color, actual_font);
    }

    @Test
    public void testFontColorNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Yellow";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        String actual_font = product.getFontColor();

        // Analyze
        assertFalse("pink" == actual_font);
    }

    @Test
    public void testPrice(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        double actual_price = product.getPrice();

        // Analyze
        assertEquals(price, actual_price);
    }

    @Test
    public void testPriceNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 2.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        double actual_price = product.getPrice();

        // Analyze
        assertFalse(0.0 == actual_price);
    }

    @Test
    public void testCoupon(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        int actual_coupon = product.getCoupon();

        // Analyze
        assertEquals(0, actual_coupon);
    }

    @Test
    public void testCouponNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 1, 11);

        // Invoke
        int actual_coupon = product.getCoupon();

        // Analyze
        assertFalse(0 == actual_coupon);
    }

    @Test
    public void testQuantity(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 11);

        // Invoke
        int actual_quantity = product.getQuantity();

        // Analyze
        assertEquals(11, actual_quantity);
    }

    @Test
    public void testQuantityNo(){
        // Setup
        int id = 99;
        String name = "Wi-Fire Dice Set";
        String color = "Blue";
        String font = "Comic Sans";
        String font_color = "Red";
        double price = 0.0;
        Product product = new Product(id, name, color, font, font_color, price, 0, 1);

        // Invoke
        int actual_quantity = product.getQuantity();

        // Analyze
        assertFalse(11 == actual_quantity);
    }


}