package com.products.api.productsapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.qos.logback.core.joran.conditional.ElseAction;
/**
 * Represents a User
 * 
 * @author Team 1
 */
public class User {
    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "User [username = %s, password = %s, first_name = %s, last_name = %s, cart_number = %d]";

    @JsonProperty("username") private String username;
    @JsonProperty("password") private String password;
    @JsonProperty("first_name") private String first_name;
    @JsonProperty("last_name") private String last_name;
    @JsonProperty("cart_number") private int cart_number;
    @JsonProperty("is_admin") private boolean is_admin;

    /**
     * Create a user with a given username, password, and cart_number
     * @param username The user's username
     * @param password The user's password
     * @param first_name The user's first name
     * @param last_name The user's surname
     * @param cart_number The user's cart number, matching them to a unique cart
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public User(@JsonProperty("username") String username, @JsonProperty("password") String password, 
    @JsonProperty("first_name") String first_name, @JsonProperty("last_name") String last_name,
    @JsonProperty("cart_number") int cart_number, @JsonProperty("is_admin") boolean is_admin) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cart_number = cart_number;
        if (this.cart_number == 2) {
            this.is_admin = true;
        }
        else {
            this.is_admin = false;
        }
    }

    /**
     * Retrieves the user's username
     * @return The user's username
     */
    public String getUsername() {return username;}


    /**
     * Retrieves the user's password
     * @return The user's password
     */
    public String getPassword() {return password;}

    /**
     * Retrieves the user's first name
     * @return The user's first name
     */
    public String getFirstName() {return first_name;}


    /**
     * Retrieves the user's surname
     * @return The user's surname
     */
    public String getLastName() {return last_name;}

    /**
     * Retrieves the user's cart number
     * @return The cart number associated with the user
     */
    public int getCartNumber() {return cart_number;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, username, password, first_name, last_name, cart_number, is_admin);
    }

    public boolean get_is_admin() {
        return this.is_admin;
    }
}