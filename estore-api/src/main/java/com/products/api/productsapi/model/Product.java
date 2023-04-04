package com.products.api.productsapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Represents a Product entity
 * 
 * @author Team 1
 */
public class Product {
    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Product [id = %d, name = %s, dice_color = %s, font_type = %s, font_color = %s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("dice_color") private String dice_color;
    @JsonProperty("font_type") private String font_type;
    @JsonProperty("font_color") private String font_color;
    @JsonProperty("price") private double price;
    @JsonProperty("coupon") private int coupon;
    @JsonProperty("quantity") private int quantity;

    /**
     * Create a product with the given id, name, font type, font color, and dice color
     * @param id The id of the product
     * @param name The name of the product
     * @param font_type The font of the product
     * @param font_color The font color of the product
     * @param Dice_color The color of the product
     * @param price the price of the dice set
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Product(@JsonProperty("id") int id, @JsonProperty("name") String name, 
    @JsonProperty("dice_color") String dice_color,
    @JsonProperty("font_type") String font_type,
    @JsonProperty("font_color")  String font_color,
    @JsonProperty("price")  Double price,
    @JsonProperty("coupon")  int coupon,
    @JsonProperty("quantity")  int quantity
     ) {
        this.id = id;
        this.name = name;
        this.dice_color = dice_color;
        this.font_type = font_type;
        this.font_color = font_color;
        this.price = price;
        this.coupon = coupon;
        this.quantity = quantity;
    }

    /**
     * Retrieves the id of the product
     * @return The id of the product
     */
    public int getId() {return id;}


    /**
     * Retrieves the name of the product
     * @return The name of the product
     */
    public String getName() {return name;}

    /**
     * Retrieves the dice color of the product
     * @return The dice_color of the product
     */
    public String getDiceColor() {return dice_color;}

    /**
     * Retrieves the font type of the product
     * @return The font type of the product
     */
    public String getFontType() {return font_type;}

    /**
     * Retrieves the font color of the product
     * @return The font color of the product
     */
    public String getFontColor() {return font_color;}

    /**
     * gets the price of a product
     * @return the price
     */
    public Double getPrice() {return price;}

    public Integer getCoupon() {return coupon;}

    public Integer getQuantity() {return quantity;}


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, id, name, dice_color, font_type, font_color, price);
    }
}