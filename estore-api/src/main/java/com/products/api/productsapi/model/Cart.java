package com.products.api.productsapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cart {
    private static final Logger LOG = Logger.getLogger(Cart.class.getName());

    static final String STRING_FORMAT = "Collection [id=%d, name=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("array") private Product[] array;

    public Cart(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("array") Product[] array) {
        this.id = id;
        this.name = name;
        this.array = array;
    }

    public int getId() {return id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    public Product[] getCartProducts() {return array;}
    
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name);
    }
}
